package com.sanhuzhen.module.musicplayer

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.musicplayer.databinding.ActivityPlayerBinding
import com.sanhuzhen.module.musicplayer.viewmodel.PlayViewModel
import com.therouter.router.Autowired
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @author: sanhuzhen
 * @date: 2024/7/19
 * @description:mv播放
 */
class PlayerActivity : BaseActivity<ActivityPlayerBinding>() {
    @Autowired
    @JvmField
    var id: String? = null

    private var isPlaying = false//是否播放音乐
    private var Url: String? = null//音乐url
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

    override fun afterCreate() {
        mViewModel.checkMusic("33894312")
        mViewModel.checkMusic.observe(this) {
            if (it.success) {
                mViewModel.apply {
                    getMusicUrl("33894312")
                    getSongDetail("33894312")
                }
            } else {
                Toast.makeText(this, "抱歉，获取失败", Toast.LENGTH_SHORT).show()
            }
        }
        mViewModel.apply {
            musicUrl.observe(this@PlayerActivity) {
                Url = it.url
                musicTime = it.time / 1000
                //计算时长
                musicTime1 = calculateTime(musicTime)
                initEvents()
            }
            songDetail.observe(this@PlayerActivity) {
                musicName = it.name
                for (i in it.ar) {
                    singer = singer + i.name + ' '
                }
                musicCoverUrl = it.al.picUrl
                musicAlbum = it.al.name
                mBinding.musicName.text = musicName
                mBinding.apply {
                    musicSinger.text = singer
                }
                Glide.with(this@PlayerActivity).load(musicCoverUrl)
                    .transform(CenterCrop(), RoundedCorners(360)).into(mBinding.musicImage)
            }
        }

    }

    override fun getViewBinding(): ActivityPlayerBinding {
        return ActivityPlayerBinding.inflate(layoutInflater)
    }

    private fun initEvents() {
        mBinding.musicPlay.setOnClickListener {
            Log.d("OnePlus", "dfsgeswgerg")
            if (!isPlaying) {
                //启动服务
                val intent = Intent(this, PlayerService::class.java)
                intent.putExtra("url", Url)
                startService(intent)
                mBinding.musicPlay.setImageResource(R.drawable.music_open)
                if (animator != null) {
                    animator?.resume()
                } else {
                    RecordRotation()//旋转动画
                }

            } else {
                //停止服务
                stopService(Intent(this, PlayerService::class.java))
                mBinding.musicPlay.setImageResource(R.drawable.music_close)
                StopRotation()
            }
            isPlaying = !isPlaying
        }

        mBinding.musicTimeTotal.text = musicTime1
    }

    override fun onDestroy() {
        super.onDestroy()
        //停止服务
        stopService(Intent(this, PlayerService::class.java))
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