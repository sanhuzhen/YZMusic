package com.sanhuzhen.module.songlist.activity

import android.animation.ValueAnimator
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.songlist.adapter.SongListAdapter
import com.sanhuzhen.module.songlist.bean.Playlist
import com.sanhuzhen.module.songlist.bean.Track
import com.sanhuzhen.module.songlist.databinding.ActivitySonglistBinding
import com.sanhuzhen.module.songlist.viewmodel.SongListViewModel
import com.therouter.router.Autowired
import com.therouter.router.Route

/**
 * @author: sanhuzhen
 * @date: 2024/7/17
 * @description:歌单详细信息
 */
@Route(path = "/songlist/songListActivity")
class SongListActivity : BaseActivity<ActivitySonglistBinding>() {
    @Autowired
    @JvmField
    var id: String? = null

    private val SongList: MutableList<Track> = mutableListOf()
    private val mViewModel by lazy {
        ViewModelProvider(this).get(SongListViewModel::class.java)
    }

    override fun afterCreate() {
        id?.let {
            mViewModel.getPlayList(it)
            mViewModel.apply {
                playList.observe(this@SongListActivity) {
                    if (it.tracks != null) {
                        SongList.addAll(it.tracks)
                        Log.d("SongListActivity", "${SongList}")
                        initSongList()
                        initToolBar(it)
                        mBinding.hiddenTextview.text = it.name
                    }

                }
            }
        }
        mBinding.back.setOnClickListener {
            finish()
        }
        mBinding.appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                // 完全折叠时显示 TextView
                mBinding.hiddenTextview.setVisibility(View.VISIBLE)
                mBinding.hiddenTextview.isSelected = true // 启用跑马灯效果

                val anim = ValueAnimator.ofInt(0, mBinding.hiddenTextview.width)
                anim.addUpdateListener { valueAnimator ->
                    val value = valueAnimator.animatedValue as Int
                    mBinding.hiddenTextview.translationX = -value.toFloat()
                }
                anim.duration = 10000 // 设置滚动持续时间，单位为毫秒
                anim.interpolator = LinearInterpolator()
                anim.repeatCount = ValueAnimator.INFINITE
                anim.start()
            } else {
                // 非完全折叠时隐藏 TextView
                mBinding.hiddenTextview.setVisibility(View.INVISIBLE)
            }
        }

    }

    override fun getViewBinding(): ActivitySonglistBinding {
        return ActivitySonglistBinding.inflate(layoutInflater)
    }

    private fun initSongList() {
        val mAdapter = SongListAdapter()
        mAdapter.submitList(SongList)
        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SongListActivity)
            adapter = mAdapter
        }
    }

    private fun initToolBar(playList: Playlist) {
        Glide.with(this).load(playList.coverImgUrl).transform(CenterCrop(), RoundedCorners(10))
            .into(mBinding.songListIv)
        Glide.with(this).load(playList.creator.avatarUrl)
            .transform(CenterCrop(), RoundedCorners(90)).into(mBinding.songListAuthor)
        mBinding.apply {
            songListName1.text = playList.name
            songListName2.text = playList.creator.nickname
            hiddenTextview.text = playList.name
        }
    }

}