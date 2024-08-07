package com.sanhuzhen.module.musicplayer

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.Player
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.lib.base.helper.SongDataHelper
import com.sanhuzhen.module.musicplayer.adapter.CommentAdapter
import com.sanhuzhen.module.musicplayer.adapter.SongListAdapter
import com.sanhuzhen.module.musicplayer.adapter.VpAdapter
import com.sanhuzhen.module.musicplayer.bean.Song
import com.sanhuzhen.module.musicplayer.databinding.ActivityMusicplayerBinding
import com.sanhuzhen.module.musicplayer.fragment.PlayFragment
import com.sanhuzhen.module.musicplayer.fragment.WordFragment
import com.sanhuzhen.module.musicplayer.helper.OnItemClickListener
import com.sanhuzhen.module.musicplayer.viewmodel.PlayViewModel
import com.therouter.router.Autowired
import com.therouter.router.Route
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.ArrayList
import kotlin.math.log

/**
 * @author: sanhuzhen
 * @date: 2024/7/21
 * @description: 音乐播放器
 */
@Route(path = "/musicplayer/musicplayerActivity")
class MusicPlayerActivity : BaseActivity<ActivityMusicplayerBinding>(), OnItemClickListener {

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
    private lateinit var songListAdapter: SongListAdapter


    //一些要传递的量
    private var currentPosition: Int = 0
    private var musicUrlList: ArrayList<String> = arrayListOf()
    private var songList: ArrayList<Song> = arrayListOf()

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
            updateSeekBar()
            isBind = true
            /**
             * 在这里进行初始化，是为了防止mBinder没有被初始化
             */
            initPlay()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBind = false
        }
    }
    private val updateSeekBarRunnable = object : Runnable {
        override fun run() {
            if (!isUserTouch) {
                mBinder.let {
                    val currentPosition = it.getCurrentPosition()
                    val duration = it.getDuration()
                    mBinding.seekBar.max = duration.toInt()
                    mBinding.seekBar.progress = currentPosition.toInt()
                    mBinding.musicTimeCurrent.text = formatTime(currentPosition)
                    if (musicIdList.isEmpty()) {
                        mBinding.musicTimeTotal.text = "00:00"
                    } else if (formatTime(duration).length <= 5) {
                        mBinding.musicTimeTotal.text = formatTime(duration)
                    }
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
        Log.d("you", "afterCreate: $musicIdList")
        initService()
        commentAdapter = CommentAdapter()
        songListAdapter = SongListAdapter(this)
        initVp()
        initView()

    }

    fun initService() {
        val intent = Intent(this@MusicPlayerActivity, MusicPlayerService::class.java)
        startService(intent)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun getViewBinding(): ActivityMusicplayerBinding {
        return ActivityMusicplayerBinding.inflate(layoutInflater)
    }

    private fun initPlay() {
        /**
         * 这里要判断一下，是否是要播放新的歌单，如果不需要，直接从Service中拿到数据，进行之前的播放
         */
        if (musicIdList.isEmpty()) {
            musicIdList = mBinder.returnMusicId()
            songList = mBinder.returnMusic()
            musicUrlList = mBinder.returnMusicUrl()
            Log.d("TAG", "onServiceConnected: $songList")
            if (mBinder.getPlayWhenReady()) {
                mBinding.musicPlay.setImageResource(R.drawable.music_open)
            } else {
                mBinding.musicPlay.setImageResource(R.drawable.music_close)
                playViewModel.isPlay(false)
            }
            currentPosition = mBinder.getMusicPosition()
            if (musicIdList.isEmpty()) {
                Toast.makeText(this@MusicPlayerActivity, "好像还没有歌单哟", Toast.LENGTH_SHORT)
                    .show()
            } else {
                playViewModel.getSongLyric(musicIdList[currentPosition])
                playViewModel.getSongDetail(musicIdList[currentPosition])
            }
        } else if (musicIdList.isNotEmpty() && musicIdList != mBinder.returnMusicId()) {
            /**
             * 这里是要判断一下，是否是切换歌单，如果是，则需要重新请求数据，先清空数据，不然会崩
             */
            mBinder.clear()
            //拼接url，完成网络请求
            musicIdList.let {
                for (i in it) {
                    BASE_URL = BASE_URL + i
                    if (i != it[it.size - 1]) {
                        BASE_URL = BASE_URL + ","
                    }
                }
            }
            initNetwork()
            currentPosition = mBinder.getMusicPosition()
            playViewModel.getSongLyric(musicIdList[currentPosition])
            playViewModel.getSongDetail(musicIdList[currentPosition])
        } else if (musicIdList.isNotEmpty() && musicIdList == mBinder.returnMusicId()) {
            musicIdList = mBinder.returnMusicId()
            songList = mBinder.returnMusic()
            musicUrlList = mBinder.returnMusicUrl()
            Log.d("TAG", "onServiceConnected: $songList")
            if (mBinder.getPlayWhenReady()) {
                mBinding.musicPlay.setImageResource(R.drawable.music_open)
            } else {
                mBinding.musicPlay.setImageResource(R.drawable.music_close)
                playViewModel.isPlay(false)
            }
            currentPosition = mBinder.getMusicPosition()
            playViewModel.getSongLyric(musicIdList[currentPosition])
            playViewModel.getSongDetail(musicIdList[currentPosition])
            Toast.makeText(this@MusicPlayerActivity, "正在播放中", Toast.LENGTH_SHORT).show()
        }
        if (mBinder.getPlayMode() == 0) {
            mBinding.musicRandom.setImageResource(R.drawable.shunxu)
        } else {
            mBinding.musicRandom.setImageResource(R.drawable.xunhuan)
        }
        if (musicIdList.isNotEmpty()) {
            val prefs = getPreferences(Context.MODE_PRIVATE)
            val isLike = prefs.getBoolean("${musicIdList[currentPosition]}", false)
            if (isLike == true) {
                mBinding.musicLike.setImageResource(R.drawable.red_heart)
            } else {
                mBinding.musicLike.setImageResource(R.drawable.heart)
            }
        }
        // 添加 ExoPlayer 播放状态监听器
        mBinder.getPlayer().addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_ENDED -> {
                        if (mBinder.getPlayer().repeatMode == Player.REPEAT_MODE_OFF) {
                            //播放结束，自动播放下一首音乐
//                            mBinder.nextMusic()
                            mBinding.seekBar.clearAnimation()
                            currentPosition = mBinder.getMusicPosition()
                            if (currentPosition >= 0 && currentPosition < musicIdList.size) {
                                // 播放结束，可以开始下一首
                                playViewModel.getSongDetail(musicIdList[currentPosition])
                                playViewModel.getSongLyric(musicIdList[currentPosition])
                            }
                        } else {
                            mBinder.getPlayer().seekTo(0)
                            mBinder.getPlayer().playWhenReady = true
                        }

                    }
                }
            }
        })
    }

    /**
     * 进行网络请求，并将数据传给Service
     */
    private fun initNetwork() {
        playViewModel.apply {
            getMusicUrl(BASE_URL!!)
            musicUrl.observe(this@MusicPlayerActivity) {
                for (i in it) {
                    musicUrlList.add(i.url)
                }
                getAllSongDetail(BASE_URL!!)
                AllSongDetail.observe(this@MusicPlayerActivity) {
                    Log.d("AllSongDetail", "$it")
                    for (i in it.songs) {
                        songList.add(i)
                    }
                    Log.d("SongListyou", "$songList")
                    currentPosition = mBinder.getMusicPosition()
                    mBinder.startPlay(musicUrlList[currentPosition])
                }
            }
            mBinder.apply {
                setMusicInfo(songList)
                setMusicUrl(musicUrlList)
                setMusicId(musicIdList)
            }

        }
    }

    //一些View的事件
    private fun initView() {
        mBinding.musicBack.setOnClickListener {
            finish()
        }
        mBinding.musicShare.setOnClickListener {
            if (musicIdList.isEmpty()) {
                Toast.makeText(this@MusicPlayerActivity, "好像还没有歌单哟", Toast.LENGTH_SHORT)
                    .show()
            } else {
                currentPosition = mBinder.getMusicPosition()
                // 创建分享意图
                val shareIntent = Intent().apply {
                    currentPosition = mBinder.getMusicPosition()
                    val musicName = songList[currentPosition].name
                    var musicArtist = ""
                    for (i in songList[currentPosition].ar) {
                        musicArtist = musicArtist + i.name + " "
                    }
                    val title =
                        "分享音乐：\n $musicName--$musicArtist\n ${musicUrlList[currentPosition]}\n 点击链接即可享受哟"
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, title)
                    type = "text/plain"
                }
                // 启动分享活动
                startActivity(Intent.createChooser(shareIntent, "选择分享应用"))
            }
        }
        mBinding.musicPlay.setOnClickListener {
            if (mBinder.getPlayWhenReady()) {
                mBinder.stopMusic()
                mBinding.musicPlay.setImageResource(R.drawable.music_close)
                playViewModel.isPlay(false)
            } else {
                if (musicIdList.isEmpty()) {
                    Toast.makeText(this@MusicPlayerActivity, "好像还没有歌单哟", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    mBinder.playMusic()
                    mBinding.musicPlay.setImageResource(R.drawable.music_open)
                    playViewModel.isPlay(true)
                }
            }
        }
        mBinding.musicNext.setOnClickListener {
            if (musicIdList.isEmpty()) {
                Toast.makeText(this@MusicPlayerActivity, "没有下一首歌曲哟", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                mBinder.nextMusic()
                currentPosition = mBinder.getMusicPosition()
                if (!mBinder.getPlayWhenReady()) {
                    mBinding.musicPlay.setImageResource(R.drawable.music_open)
                    playViewModel.isPlay(true)
                }
                val prefs = getPreferences(Context.MODE_PRIVATE)
                val isLike = prefs.getBoolean("${musicIdList[currentPosition]}", false)
                if (isLike == true) {
                    mBinding.musicLike.setImageResource(R.drawable.red_heart)
                } else {
                    mBinding.musicLike.setImageResource(R.drawable.heart)
                }
                playViewModel.getSongDetail(musicIdList[currentPosition])
                playViewModel.getSongLyric(musicIdList[currentPosition])
            }
        }
        mBinding.musicFront.setOnClickListener {
            if (musicIdList.isEmpty()) {
                Toast.makeText(this@MusicPlayerActivity, "没有上一首歌曲哟", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                mBinder.preMusic()
                currentPosition = mBinder.getMusicPosition()
                if (!mBinder.getPlayWhenReady()) {
                    mBinding.musicPlay.setImageResource(R.drawable.music_open)
                    playViewModel.isPlay(true)
                }
                val prefs = getPreferences(Context.MODE_PRIVATE)
                val isLike = prefs.getBoolean("${musicIdList[currentPosition]}", false)
                if (isLike == true) {
                    mBinding.musicLike.setImageResource(R.drawable.red_heart)
                } else {
                    mBinding.musicLike.setImageResource(R.drawable.heart)
                }
                playViewModel.getSongDetail(musicIdList[currentPosition])
                playViewModel.getSongLyric(musicIdList[currentPosition])
            }
        }
        //对seekBar的监听
        mBinding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //进度条滑动
                if (fromUser) {
                    mBinder.seekTo(progress.toLong())
                    mBinding.musicTimeCurrent.text = formatTime(progress.toLong())
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
            if (musicIdList.isEmpty()) {
                Toast.makeText(this@MusicPlayerActivity, "好像还没有歌曲播放哟", Toast.LENGTH_SHORT)
                    .show()
            } else {
                currentPosition = mBinder.getMusicPosition()
                val prefs = getPreferences(Context.MODE_PRIVATE)
                val editor = prefs.edit()
                val isLike = prefs.getBoolean("${musicIdList[currentPosition]}", false)
                if (isLike == true) {
                    mBinding.musicLike.setImageResource(R.drawable.heart)
                    editor.putBoolean("${musicIdList[currentPosition]}", false)
                    editor.apply()
                } else {
                    mBinding.musicLike.setImageResource(R.drawable.red_heart)
                    editor.putBoolean("${musicIdList[currentPosition]}", true)
                    editor.apply()
                }
            }
        }

        mBinding.musicComment.setOnClickListener {
            showCommentBottomSheetDialog()
        }

        mBinding.musicList.setOnClickListener {
            showSongListBottomSheetDialog()
        }

        mBinding.musicRandom.setOnClickListener {
            if (mBinder.getPlayMode() == 0) {
                mBinder.setPlayMode(1)
                mBinding.musicRandom.setImageResource(R.drawable.xunhuan)
                Toast.makeText(this@MusicPlayerActivity, "单曲循环", Toast.LENGTH_SHORT).show()
            } else {
                mBinder.setPlayMode(0)
                mBinding.musicRandom.setImageResource(R.drawable.shunxu)
                Toast.makeText(this@MusicPlayerActivity, "顺序播放", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.musicDownload.setOnClickListener {
            if (musicIdList.isEmpty()) {
                Toast.makeText(this@MusicPlayerActivity, "好像还没有歌曲播放哟", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                currentPosition = mBinder.getMusicPosition()
                val dpHelper = SongDataHelper(this, "song", 1)
                dpHelper.addBook(
                    songList[currentPosition].name,
                    songList[currentPosition].ar[0].name,
                    musicIdList[currentPosition],
                    songList[currentPosition].al.picUrl
                )
                Toast.makeText(this, "下载成功", Toast.LENGTH_SHORT).show()
            }

        }

    }


    @SuppressLint("ResourceAsColor")
    private fun showSongListBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.songlist_bottom_sheet, null)

        val recyclerView = bottomSheetView.findViewById<RecyclerView>(R.id.rv_singlist)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = songListAdapter
        songListAdapter.submitList(mBinder.returnMusic())
        currentPosition = mBinder.getMusicPosition()
        recyclerView.scrollToPosition(currentPosition)
//        recyclerView.get(currentPosition).setBackgroundColor(R.color.pink)
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()

    }

    private fun showCommentBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(R.layout.comment_bottom_sheet, null)

        val recyclerView = bottomSheetView.findViewById<RecyclerView>(R.id.rv_comment)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = commentAdapter

        if (musicIdList.isNotEmpty()) {
            currentPosition = mBinder.getMusicPosition()
            lifecycleScope.launch {
                playViewModel.getComments("0", musicIdList[currentPosition], "3")
                    .collectLatest { pagingData ->
                        commentAdapter.submitData(pagingData)
                    }
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

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, R.anim.slide_out)
    }


    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, R.anim.slide_out)
    }


    //格式化时间
    @SuppressLint("DefaultLocale")
    private fun formatTime(milliseconds: Long): String {
        val minutes = (milliseconds / 1000) / 60
        val seconds = (milliseconds / 1000) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onItemClick(position: Int) {
        mBinder.changeMusicInfo(position)
        currentPosition = position
        if (!mBinder.getPlayWhenReady()) {
            mBinding.musicPlay.setImageResource(R.drawable.music_open)
            playViewModel.isPlay(true)
        }
        playViewModel.getSongDetail(musicIdList[currentPosition])
        playViewModel.getSongLyric(musicIdList[currentPosition])
    }


}