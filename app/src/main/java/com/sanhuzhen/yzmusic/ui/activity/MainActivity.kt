package com.sanhuzhen.yzmusic.ui.activity

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
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
import com.sanhuzhen.module.musicplayer.helper.OnItemClickListener
import com.sanhuzhen.yzmusic.R
import com.sanhuzhen.yzmusic.adapter.VpAdapter
import com.sanhuzhen.yzmusic.databinding.ActivityMainBinding
import com.therouter.TheRouter

class MainActivity : BaseActivity<ActivityMainBinding>(), OnItemClickListener {
    private var mBinder: MusicPlayerService.MusicBinder? = null
    private var animator: ObjectAnimator? = null
    private lateinit var songListAdapter: SongListAdapter
    private val mViewModel by lazy {
        ViewModelProvider(this)[BaseViewModel::class.java]
    }
    private var musicDetail = arrayListOf<Song>()
    private var currentPosition = 0

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBinder = service as MusicPlayerService.MusicBinder
            mBinder?.let { binder ->
                if (binder.getPlayWhenReady()) {
                    mBinding.musicIvPlay.setImageResource(R.drawable.music_open)
                } else {
                    mBinding.musicIvPlay.setImageResource(R.drawable.music_close)
                }
                binder.getPlayer().addListener(object : Player.Listener {
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        Log.d("MusicPlayerService", "onPlaybackStateChanged: $playbackState")
                        when (playbackState) {
                            Player.STATE_ENDED -> {
                                if (binder.getPlayer().repeatMode == Player.REPEAT_MODE_OFF) {
                                    musicDetail = binder.returnMusic()
                                    Log.d("MainActivity", "onPlaybackStateChanged: ${musicDetail}")
                                    currentPosition = binder.getMusicPosition()
                                    if (musicDetail.isNotEmpty() && currentPosition >= 0 && currentPosition < musicDetail.size) {
                                        handleMusicPlaybackComplete()
                                    } else {
                                        Log.e(
                                            "MainActivity",
                                            "Invalid currentPosition or empty musicDetail list."
                                        )
                                    }
                                } else {
                                    binder.seekTo(0)
                                }
                            }
                        }
                    }
                })
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBinder = null
        }
    }

    override fun afterCreate() {
        songListAdapter = SongListAdapter(this)
        initBottomNav()
        initNavigationView()
        initEvent()
        bindService()
        initMusic()
    }

    private fun initMusic() {
        mBinding.musicIvPlay.setOnClickListener {
            mBinder?.let { binder ->
                if (binder.getPlayWhenReady()) {
                    mBinding.musicIvPlay.setImageResource(R.drawable.music_close)
                    binder.stopMusic()
                    animator?.pause()
                } else {
                    if (musicDetail.isEmpty()) {
                        Toast.makeText(this@MainActivity, "请先添加歌曲", Toast.LENGTH_SHORT).show()
                    } else {
                        mBinding.musicIvPlay.setImageResource(R.drawable.music_open)
                        binder.playMusic()
                        if (animator == null) {
                            RecordRotation()
                        } else {
                            animator?.resume()
                        }
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
        songListAdapter.submitList(mBinder?.returnMusic())
        currentPosition = mBinder?.getMusicPosition() ?: 0
        recyclerView.scrollToPosition(currentPosition)
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    private fun bindService() {
        val intent = Intent(this, MusicPlayerService::class.java)
        startService(intent)
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    private fun initBottomNav() {
        mBinding.mainVp2.apply {
            adapter = VpAdapter(this@MainActivity)
            registerOnPageChangeCallback(object :
                androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mBinding.mainNav.menu.getItem(position).isChecked = true
                }
            })
            isUserInputEnabled = false
        }
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
        mBinder?.let { binder ->
            musicDetail = binder.returnMusic()
            Log.d("onRestart", "$musicDetail")
            if (musicDetail.isNotEmpty()) {
                currentPosition = binder.getMusicPosition()
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
            if (binder.getPlayWhenReady()) {
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
    }

    private fun RecordRotation() {
        animator = ObjectAnimator.ofFloat(mBinding.musicIv, "rotation", 0f, 360f).apply {
            duration = 20000
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            start()
        }
    }

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

    override fun onDestroy() {
        super.onDestroy()
        animator?.cancel()
        animator = null
        mBinder = null
        stopService(Intent(this, MusicPlayerService::class.java))
        unbindService(connection)
    }

    override fun onItemClick(position: Int) {
        mBinder?.changeMusicInfo(position)
        currentPosition = position
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