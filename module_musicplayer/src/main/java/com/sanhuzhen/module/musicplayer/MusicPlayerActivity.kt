package com.sanhuzhen.module.musicplayer

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.SeekBar
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.musicplayer.adapter.CommentAdapter
import com.sanhuzhen.module.musicplayer.adapter.VpAdapter
import com.sanhuzhen.module.musicplayer.databinding.ActivityMusicplayerBinding
import com.sanhuzhen.module.musicplayer.fragment.PlayFragment
import com.sanhuzhen.module.musicplayer.fragment.WordFragment
import com.sanhuzhen.module.musicplayer.viewmodel.PlayViewModel
import com.therouter.router.Autowired
import com.therouter.router.Route
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.ArrayList

/**
 * @author: sanhuzhen
 * @date: 2024/7/21
 * @description: 音乐播放器
 */
@Route(path = "/musicplayer/musicplayerActivity")
class MusicPlayerActivity : BaseActivity<ActivityMusicplayerBinding>() {

    @Autowired(name = "SongList")
    @JvmField
    var musicIdList: ArrayList<String> = arrayListOf()

    //判断Service是否绑定
    private var isBind = false

    //判断用户是否拖动SeekBar
    private var isUserTouch = false

    //用Handler来更新UI
    private val handler = Handler(Looper.getMainLooper())

    private var BASE_URL: String? = ""

    private lateinit var mBinder: MusicPlayerService.MusicBinder

    private lateinit var commentAdapter: CommentAdapter

    //一些要传递的量
    private var musicName = ""
    private var musicAuthor = ""
    private var musicCoverUrl = ""
    private var currentPosition: Int = 0
    private var musicUrlList: ArrayList<String> = arrayListOf()

    private val playViewModel by lazy {
        ViewModelProvider(this)[PlayViewModel::class.java]
    }

    private val vpAdapter by lazy {
        VpAdapter(this)
    }

    /**
     * Service和Activity的通信
     */
    private val connection = object : android.content.ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            // 获取Service的Binder对象
            mBinder = service as MusicPlayerService.MusicBinder
            currentPosition = mBinder.getMusicPosition()
            mBinder.startPlay(musicUrlList[currentPosition])
            updateSeekBar()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBind = false
        }
    }
    private val updateSeekBarRunnable = object : Runnable {
        override fun run() {
            if (!isUserTouch && isBind) {
                mBinder.let {
                    val currentPosition = it.getCurrentPosition()
                    val duration = it.getDuration()
                    mBinding.seekBar.max = duration.toInt()
                    mBinding.seekBar.progress = currentPosition.toInt()
                    mBinding.musicTimeCurrent.text = formatTime(currentPosition)
                    mBinding.musicTimeTotal.text = formatTime(duration)
                }
            }
            //每隔1秒更新进度条
            handler.postDelayed(this, 1000)
        }
    }

    private fun updateSeekBar() {
        handler.post(updateSeekBarRunnable)
    }

    override fun afterCreate() {
        Log.d("SongListyou","$musicIdList")
        //拼接url，完成网络请求
        musicIdList.let {
            for (i in it) {
                BASE_URL = BASE_URL + i
                if (i != it[it.size - 1]) {
                    BASE_URL = BASE_URL + ","
                }
            }
        }
        commentAdapter = CommentAdapter()
        initBackPress()
        initVp()
        initNetwork()
        initView()
    }

    override fun getViewBinding(): ActivityMusicplayerBinding {
        return ActivityMusicplayerBinding.inflate(layoutInflater)
    }

    private fun initNetwork() {
        playViewModel.apply {
            getMusicUrl(BASE_URL!!)
            musicUrl.observe(this@MusicPlayerActivity) {
                for (i in it) {
                    musicUrlList.add(i.url)
                }
                //在这里进行Service的绑定，当musicUrlList不为空时，进行Service的绑定
                getSongDetail(musicIdList[currentPosition])
                songDetail.observe(this@MusicPlayerActivity) {
                    musicName = it.name
                    for (i in it.ar) {
                        musicAuthor = i.name + ' '
                    }
                    musicCoverUrl = it.al.picUrl
                    val intent = Intent(this@MusicPlayerActivity, MusicPlayerService::class.java)
                    if (!isBind) {
                        intent.apply {
                            putStringArrayListExtra("MusicUrlList", musicUrlList)
                            putExtra("MusicName", musicName)
                            putExtra("MusicAuthor", musicAuthor)
                            putExtra("MusicCoverUrl", musicCoverUrl)
                        }
                        startService(intent)
                        bindService(intent, connection, Context.BIND_AUTO_CREATE)
                        isBind = true
                    }
                }
                Log.d("TAG", "initNetwork: $musicUrlList")
            }

        }
    }

    //一些View的事件
    private fun initView() {
        mBinding.musicBack.setOnClickListener {
            finish()
        }
        mBinding.musicShare.setOnClickListener {
            currentPosition = mBinder.getMusicPosition()
            // 创建分享意图
            val shareIntent = Intent().apply {
                val title = "$musicName -- $musicAuthor\n ${musicUrlList[currentPosition]}"
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, title)
                type = "text/plain"
            }
            // 启动分享活动
            startActivity(Intent.createChooser(shareIntent, "选择分享应用"))
        }
        mBinding.musicPlay.setOnClickListener {
            if (mBinder.getPlayWhenReady()) {
                mBinder.stopMusic()
                mBinding.musicPlay.setImageResource(R.drawable.music_close)
                playViewModel.isPlay(false)
            } else {
                mBinder.playMusic()
                mBinding.musicPlay.setImageResource(R.drawable.music_open)
                playViewModel.isPlay(true)
            }
        }
        mBinding.musicNext.setOnClickListener {
            mBinder.nextMusic()
            currentPosition = mBinder.getMusicPosition()
            if (!mBinder.getPlayWhenReady()) {
                mBinding.musicPlay.setImageResource(R.drawable.music_open)
                playViewModel.isPlay(true)
            }
            playViewModel.getSongDetail(musicIdList[currentPosition])
        }
        mBinding.musicFront.setOnClickListener {
            mBinder.preMusic()
            currentPosition = mBinder.getMusicPosition()
            if (!mBinder.getPlayWhenReady()) {
                mBinding.musicPlay.setImageResource(R.drawable.music_open)
                playViewModel.isPlay(true)
            }
            playViewModel.getSongDetail(musicIdList[currentPosition])
        }
        //对seekBar的监听
        mBinding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //进度条滑动
                if (fromUser) {
                    mBinder.seekTo(progress.toLong())
                }
                //进度条滑动结束，自动播放下一首
                if (progress >= seekBar!!.max) {
                    //更新Fragment中的数据
                    currentPosition = mBinder.getMusicPosition()
                    Log.d("TAG", "onProgressChanged: $currentPosition")
                    playViewModel.getSongDetail(musicIdList[currentPosition])

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                isUserTouch = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                isUserTouch = false
            }
        })
        mBinding.musicLike.setOnClickListener {

        }

        mBinding.musicComment.setOnClickListener {
            showBottomSheetDialog()
        }
    }


    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.comment_bottom_sheet, null)

        val recyclerView = bottomSheetView.findViewById<RecyclerView>(R.id.rv_comment)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = commentAdapter

        currentPosition = mBinder.getMusicPosition()
        lifecycleScope.launch {
            playViewModel.getComments("0",musicIdList[currentPosition],"3").collectLatest { pagingData ->
                commentAdapter.submitData(pagingData)
            }
        }

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }

    //固定Vp2
    private fun initVp() {
        vpAdapter.apply {
            addFragment(PlayFragment())
            addFragment(WordFragment())
        }
        mBinding.musicViewpager.adapter = vpAdapter
        TabLayoutMediator(mBinding.musicTablayout, mBinding.musicViewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "●"
                }

                1 -> {
                    tab.text = "●"
                }
            }
        }.attach()
    }


    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
        //移除更新进度条的任务，避免内存泄漏
        handler.removeCallbacks(updateSeekBarRunnable)
    }

    //一些动画效果
    private fun initBackPress() {
        // 添加返回按钮的回调函数
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 在这里处理返回按钮事件
                handleCustomOnBackPressed()
            }
        })
    }

    private fun handleCustomOnBackPressed() {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }

    //格式化时间
    @SuppressLint("DefaultLocale")
    private fun formatTime(milliseconds: Long): String {
        val minutes = (milliseconds / 1000) / 60
        val seconds = (milliseconds / 1000) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }


}