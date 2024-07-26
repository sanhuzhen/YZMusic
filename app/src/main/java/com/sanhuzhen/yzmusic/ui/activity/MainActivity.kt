package com.sanhuzhen.yzmusic.ui.activity

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.Player
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.mine.viewmodel.BaseViewModel
import com.sanhuzhen.module.musicplayer.MusicPlayerService
import com.sanhuzhen.module.musicplayer.adapter.SongListAdapter
import com.sanhuzhen.module.musicplayer.bean.Song
import com.sanhuzhen.yzmusic.R
import com.sanhuzhen.yzmusic.adapter.VpAdapter
import com.sanhuzhen.yzmusic.databinding.ActivityMainBinding
import com.therouter.TheRouter

/**
 * @author: sanhuzhen
 * @date: 2024/7/14
 * @description:
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var mBinder: MusicPlayerService.MusicBinder

    private var animator: ObjectAnimator? = null // 旋转动画

    private lateinit var songListAdapter: SongListAdapter

    private val mViewModel by lazy {
        ViewModelProvider(this)[BaseViewModel::class.java]
    }

    /**
     * 音乐的信息
     */
    private var musicDetail = arrayListOf<Song>()
    private var currentPosition = 0

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    /**
     * Service和Activity的通信
     */
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBinder = service as MusicPlayerService.MusicBinder
            if (mBinder.getPlayWhenReady()) {
                mBinding.musicIvPlay.setImageResource(R.drawable.music_open)
            } else {
                mBinding.musicIvPlay.setImageResource(R.drawable.music_close)
            }
            mBinder.getPlayer().addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    Log.d("MusicPlayerService", "onPlaybackStateChanged: $playbackState")
                    when (playbackState) {
                        Player.STATE_ENDED -> {
                            if (mBinder.getPlayer().repeatMode == Player.REPEAT_MODE_OFF) {
                                musicDetail = mBinder.returnMusic()
                                Log.d("MainActivity", "onPlaybackStateChanged: ${musicDetail}")
                                currentPosition = mBinder.getMusicPosition()
                                if (musicDetail.isNotEmpty() && currentPosition >= 0 && currentPosition < musicDetail.size) {
                                    handleMusicPlaybackComplete()
                                } else {
                                    Log.e(
                                        "MainActivity",
                                        "Invalid currentPosition or empty musicDetail list."
                                    )
                                }
                            } else {
                                mBinder.seekTo(0)
                            }
                        }
                    }
                }
            })

        }

        override fun onServiceDisconnected(name: ComponentName?) {}
    }

    override fun afterCreate() {
        songListAdapter = SongListAdapter()
        // 底部导航栏
        initBottomNav()
        // 抽屉式菜单
        initNavigationView()
        initEvent()
        bindService()
        initMusic()
    }

    // 固定一下播放栏的一些UI点击事件
    private fun initMusic() {
        mBinding.musicIvPlay.setOnClickListener {
            if (mBinder.getPlayWhenReady()) {
                mBinding.musicIvPlay.setImageResource(R.drawable.music_close)
                mBinder.stopMusic()
                animator?.pause()
            } else {
                if (musicDetail.isEmpty()) {
                    Toast.makeText(this@MainActivity, "请先添加歌曲", Toast.LENGTH_SHORT).show()
                } else {
                    mBinding.musicIvPlay.setImageResource(R.drawable.music_open)
                    mBinder.playMusic()
                    if (animator == null) {
                        RecordRotation()
                    } else {
                        animator?.resume()
                    }
                }
            }
        }
        mBinding.mainCard.setOnClickListener {
            TheRouter.build("/musicplayer/musicplayerActivity").navigation()
            overridePendingTransition(R.anim.slide_in, 0)
        }

        mBinding.musicIvList.setOnClickListener {
            showSongListBottomSheetDialog()
        }

    }

    private fun showSongListBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(
            com.sanhuzhen.module.musicplayer.R.layout.songlist_bottom_sheet,
            null
        )

        val recyclerView =
            bottomSheetView.findViewById<RecyclerView>(com.sanhuzhen.module.musicplayer.R.id.rv_singlist)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = songListAdapter
        songListAdapter.submitList(mBinder.returnMusic())
        currentPosition = mBinder.getMusicPosition()
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    // Service绑定
    private fun bindService() {
        val intent = Intent(this, MusicPlayerService::class.java)
        startService(intent)
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    private fun initBottomNav() {
        // Vp2设置
        mBinding.mainVp2.apply {
            adapter = VpAdapter(this@MainActivity)
            registerOnPageChangeCallback(object :
                androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mBinding.mainNav.menu.getItem(position).isChecked = true
                }
            })
            // 禁止滑动
            isUserInputEnabled = false
        }
        // Nav设置
        mBinding.mainNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_nav_home -> mBinding.mainVp2.currentItem = 0
                R.id.menu_nav_hot -> mBinding.mainVp2.currentItem = 1
                R.id.menu_nav_mine -> mBinding.mainVp2.currentItem = 2
            }
            true
        }
    }

    private fun initNavigationView() {
        val sp = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        mBinding.mainToolbarMenu.setOnClickListener {
            mBinding.mainDrawer.openDrawer(GravityCompat.START)
            val headerView = mBinding.mainNavView.getHeaderView(0)
            val userImage = headerView.findViewById<ImageView>(R.id.nav_header_iv)
            val userName = headerView.findViewById<TextView>(R.id.nav_header_tv_name)
            val userId = headerView.findViewById<TextView>(R.id.nav_header_tv_email)
            val id = sp.getLong("id", 0)
            if (id != 0L) {
                mViewModel.getBase(id)
                mViewModel.mBase.observe(this) {
                    Glide.with(this).load(it.profile.avatarUrl)
                        .transform(CenterCrop(), RoundedCorners(20)).into(userImage)
                    userName.text = it.profile.nickname
                    userId.text = it.profile.userId.toString()
                }
            } else {
                userImage.setImageResource(R.drawable.ic_my)
                userName.text = "游客"
                userId.text = "不登录，看啥ID"

            }
        }
        mBinding.mainNavView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_login -> {
                    val id = sp.getLong("id", 0)
                    if (id == 0L) {
                        Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show()
                    } else {
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle("询问")
                        builder.setMessage("是否要退出登录")
                        builder.setPositiveButton("确定") { dialog, _ ->
                            val edit = sp.edit()
                            edit.remove("id")
                            edit.apply()
                            TheRouter.build("/login/LoginActivity").navigation()
                            dialog.dismiss()
                            mBinding.mainDrawer.closeDrawers()
                        }
                        builder.setNegativeButton("取消") { dialog, _ ->
                            dialog.dismiss()
                        }
                        val dialog = builder.create()
                        dialog.show()

                    }
                }

                R.id.nav_we -> {
                    Toast.makeText(this, "我们是牛马", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    private fun initEvent() {
        mBinding.mainToolbarSearch.setOnClickListener {
            TheRouter.build("/search/SearchActivity").navigation()
        }
    }

    override fun onRestart() {
        super.onRestart()
        musicDetail = mBinder.returnMusic()
        Log.d("onRestart", "$musicDetail")
        if (musicDetail.isNotEmpty()) {
            currentPosition = mBinder.getMusicPosition()
            mBinding.apply {
                musicTvName.text = musicDetail[currentPosition].name
                var musicAr = ""
                for (i in musicDetail[currentPosition].ar) {
                    musicAr += i.name + " "
                }
                musicTvArtist.text = musicAr
                Glide.with(this@MainActivity).load(musicDetail[currentPosition].al.picUrl)
                    .transform(CenterCrop(), RoundedCorners(360)).into(musicIv)
                if (mBinding.musicTvName.length() > 5) {
                    val marqueeTextView = mBinding.musicTvName
                    marqueeTextView.isSelected = true
                }
            }
        }
        if (mBinder.getPlayWhenReady()) {
            mBinding.musicIvPlay.setImageResource(R.drawable.music_open)
            if (animator == null) {
                RecordRotation()
            } else {
                animator?.resume()
            }
        } else {
            mBinding.musicIvPlay.setImageResource(R.drawable.music_close)
            animator?.pause()
        }
    }

    // 旋转动画
    private fun RecordRotation() {
        // 打碟效果
        animator = ObjectAnimator.ofFloat(mBinding.musicIv, "rotation", 0f, 360f).apply {
            duration = 20000 // 旋转时间
            interpolator = LinearInterpolator() // 匀速
            repeatCount = ValueAnimator.INFINITE // 设置动画重复次数(-1代表一直转)
            repeatMode = ValueAnimator.RESTART // 动画重复模式
            start() // 动画启动
        }
    }

    // 更新音乐结束后的播放栏的UI信息
    private fun handleMusicPlaybackComplete() {
        if (musicDetail.isNotEmpty() && currentPosition >= 0 && currentPosition < musicDetail.size) {
            mBinding.apply {
                musicTvName.text = musicDetail[currentPosition].name
                var musicAr = ""
                for (i in musicDetail[currentPosition].ar) {
                    musicAr += i.name + " "
                }
                musicTvArtist.text = musicAr
                Glide.with(this@MainActivity).load(musicDetail[currentPosition].al.picUrl)
                    .transform(CenterCrop(), RoundedCorners(360)).into(musicIv)
            }
        } else {
            Log.e("MainActivity", "Invalid currentPosition or empty musicDetail list.")
        }
    }
}