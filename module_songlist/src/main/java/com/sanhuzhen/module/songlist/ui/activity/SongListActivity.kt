package com.sanhuzhen.module.songlist.ui.activity

import android.util.Log
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
import com.therouter.TheRouter
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
    private val SongLists = arrayListOf<String>()

    override fun afterCreate() {
        id?.let {
            mViewModel.getPlayList(it)
            mViewModel.apply {
                playList.observe(this@SongListActivity) {
                    if (it.tracks != null) {
                        SongList.addAll(it.tracks)
                        mBinding.songNum.text = SongList.size.toString()
                        Log.d("SongListActivity", "${SongList}")
                        initSongList()
                        initToolBar(it)
//                        mBinding.toolbar.isSelected = true // 启用跑马灯效果
//                        val anim = ValueAnimator.ofInt(0, mBinding.toolbar.width)
//                        anim.addUpdateListener { valueAnimator ->
//                            val value = valueAnimator.animatedValue as Int
//                            mBinding.toolbar.translationX = -value.toFloat()
//                        }
//                        anim.duration = 10000 // 设置滚动持续时间，单位为毫秒
//                        anim.interpolator = LinearInterpolator()
//                        anim.repeatCount = ValueAnimator.INFINITE
//                        anim.start()
                        mBinding.collapsingToolbar.title = it.name
                        for (i in SongList) {
                            SongLists.add(i.id.toString())
                        }
                    }

                }
            }
        }
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        mBinding.toolbar.setNavigationOnClickListener {
            finishAfterTransition()
        }
        mBinding.play.setOnClickListener {
            TheRouter.build("/musicplayer/musicplayerActivity").withObject("SongList", SongLists)
                .navigation()
        }
    }

    override fun getViewBinding(): ActivitySonglistBinding {
        return ActivitySonglistBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()
        for (i in SongList) {
            SongLists.add(i.id.toString())
        }
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
//            mBinding.toolbar.title = playList.name
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAfterTransition()
    }

}