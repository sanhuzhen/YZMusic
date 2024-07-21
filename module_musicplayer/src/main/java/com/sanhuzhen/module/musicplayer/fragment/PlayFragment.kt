package com.sanhuzhen.module.musicplayer.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.HorizontalScrollView
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.musicplayer.R
import com.sanhuzhen.module.musicplayer.adapter.SingerAdapter
import com.sanhuzhen.module.musicplayer.bean.Ar
import com.sanhuzhen.module.musicplayer.databinding.FragmentPlayBinding
import com.sanhuzhen.module.musicplayer.viewmodel.PlayViewModel

class PlayFragment : BaseFragment<FragmentPlayBinding>() {

    private var isPlaying = true//播放状态
    private var isUserSeeking = false//用户是否正在拖动SeekBar

    private lateinit var mAdapter: SingerAdapter
    private var singerList = mutableListOf<Ar>()
    private var animator: ObjectAnimator? = null//旋转动画

    private val mViewModel by lazy {
        ViewModelProvider(requireActivity())[PlayViewModel::class.java]
    }

    override fun getViewBinding(): FragmentPlayBinding {
        return FragmentPlayBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
//        RecordRotation()
//        mBinding.musicPlay.setOnClickListener {
//            if (isPlaying) {
//                mViewModel.isPlay(false)
//                mBinding.musicPlay.setImageResource(R.drawable.music_close)
//                animator?.pause()
//            } else {
//                mViewModel.isPlay(true)
//                mBinding.musicPlay.setImageResource(R.drawable.music_open)
//                animator?.resume()
//            }
//            isPlaying = !isPlaying
//        }
//        mBinding.musicNext.setOnClickListener {
//            mViewModel.changeMusic(1)
//            if (!isPlaying) {
//                mBinding.musicPlay.setImageResource(R.drawable.music_open)
//                animator?.resume()
//                isPlaying = true
//            }
//        }
//        mBinding.musicFront.setOnClickListener {
//            mViewModel.changeMusic(0)
//            if (!isPlaying) {
//                mBinding.musicPlay.setImageResource(R.drawable.music_open)
//                animator?.resume()
//                isPlaying = true
//            }
//        }
//        initUIEvent()

    }

    /**
     * 从网上拿到的数据绑定到UI上
     */
    private fun initUIEvent() {
        mViewModel.songDetail.observe(requireActivity()) {
            val MusicName = it.name
            val MusicCoverUrl = it.al.picUrl
            mAdapter = SingerAdapter()
            singerList = it.ar.toMutableList()
            mAdapter.submitList(singerList)
            mBinding.apply {
                recyclerView.apply {
                    adapter = mAdapter
                    layoutManager =
                        LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
                }
                musicName.text = MusicName
            }
            Glide.with(this).load(MusicCoverUrl).transform(CenterCrop(), RoundedCorners(360))
                .into(mBinding.musicImage)
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

}