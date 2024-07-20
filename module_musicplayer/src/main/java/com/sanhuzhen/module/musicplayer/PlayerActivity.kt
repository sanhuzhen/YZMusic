package com.sanhuzhen.module.musicplayer

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.util.UnstableApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.musicplayer.bean.Data
import com.sanhuzhen.module.musicplayer.databinding.ActivityPlayerBinding
import com.sanhuzhen.module.musicplayer.viewmodel.PlayViewModel
import com.therouter.router.Autowired
import java.util.ArrayList

/**
 * @author: sanhuzhen
 * @date: 2024/7/19
 * @description:music播放
 */
@UnstableApi
class PlayerActivity : BaseActivity<ActivityPlayerBinding>() {
    @Autowired
    @JvmField
    var idList: ArrayList<String>? = null

    private var mBinder: PlayerService.MusicBinder?=null
    private var myService: PlayerService? = null//服务
    private var BASE_URL: String? = ""
    private var isPlaying = false//是否播放音乐
    private var bound = false//是否绑定服务
    private var isUserTouchingSeekBar = false
    private var MusicUrl: ArrayList<String>? = ArrayList()//音乐url
    private var musicName: String? = null//音乐名称
    private var singer: String? = null//歌手
    private var musicAlbum: String? = null//专辑
    private var musicCoverUrl: String? = null//封面url
    private var musicTime: Long? = null//时长
    private var musicTime1: String? = null//转换的时间
    private var animator: ObjectAnimator? = null//旋转动画


    private val mViewModel by lazy {
        ViewModelProvider(this)[PlayViewModel::class.java]
    }

    private val connection = object : ServiceConnection {
        @OptIn(UnstableApi::class)
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBinder = service as PlayerService.MusicBinder
            mBinder?.setSongList(MusicUrl!!)
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }

    }


    override fun afterCreate() {
        idList = arrayListOf("33894312","33894311")
        //拼接url
        idList?.let {
            for (i in it) {
                BASE_URL = BASE_URL + i
                //判断是否带逗号
                if (i != it[it.size - 1]) {
                    BASE_URL = BASE_URL + ","
                }
            }
            Log.d("BASE_URL", "$BASE_URL")
        }
        BASE_URL?.let {
            mViewModel.apply {
                getMusicUrl(it)
            }
        }
        mViewModel.musicUrl.observe(this) {
            for (i in it) {
                MusicUrl?.add(i.url)
                Log.d("you", "${i.url}")
            }
            initEvent()
        }

        Thread.sleep(1000)
    }

    //一些UI的点击事件
    @OptIn(UnstableApi::class)
    private fun initEvent() {
        mBinding.apply {
            musicPlay.setOnClickListener {
                Log.d("TAG", "onClick: ")
                if (isPlaying) {
                    mBinder?.pauseMusic()
                    musicPlay.setImageResource(R.drawable.music_close)
                } else {
                    mBinder?.playMusic()
                    musicPlay.setImageResource(R.drawable.music_open)
                }
                isPlaying = !isPlaying
            }
            musicNext.setOnClickListener {
                mBinder?.playNext()
            }
            musicFront.setOnClickListener {
                mBinder?.playPrevious()
            }
        }
    }

    @OptIn(UnstableApi::class)
    override fun onStart() {
        super.onStart()
        if (!bound) {
            val intent = Intent(this, PlayerService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
            isPlaying = true
            bound = true
        }
    }

    override fun getViewBinding(): ActivityPlayerBinding {
        return ActivityPlayerBinding.inflate(layoutInflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        //停止服务,不需要停止，要在后台一直播放
//        stopService(Intent(this, PlayerService::class.java))
    }

    //事件转换器
    private fun calculateTime(time: Long?): String {
        if (time == null) {
            return "00:00"
        } else {
            val minute = time / 60
            val second = time % 60
            return if (minute < 10) {
                if (second < 10) {
                    "0$minute:0$second"
                } else {
                    "0$minute:$second"
                }
            } else {
                if (second < 10) {
                    "$minute:0$second"
                } else {
                    "$minute:$second"
                }
            }
        }
    }

    //旋转动画
    private fun RecordRotation() {
        //打碟效果
        animator = ObjectAnimator.ofFloat(mBinding.cardView1, "rotation", 0f, 360f).apply {
            duration = 20000 // 旋转时间
            interpolator = LinearInterpolator() // 匀速
            repeatCount = ValueAnimator.INFINITE // 设置动画重复次数(-1代表一直转)
            repeatMode = ValueAnimator.RESTART // 动画重复模式
            start() // 动画启动
        }
    }

    //停止旋转动画
    private fun StopRotation() {
        animator?.pause()
    }


}