package com.sanhuzhen.module.mvplayer

import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.mvplayer.adapter.ArtistRvAdapter
import com.sanhuzhen.module.mvplayer.adapter.MvListAdapter
import com.sanhuzhen.module.mvplayer.bean.SimiMvData
import com.sanhuzhen.module.mvplayer.bean.mData
import com.sanhuzhen.module.mvplayer.databinding.ActivityMvplayerBinding
import com.sanhuzhen.module.mvplayer.viewmodel.MvPlayerViewModel
import com.therouter.router.Autowired
import com.therouter.router.Route

/**
 * @author: sanhuzhen
 * @date: 2024/7/18
 * @description: MV播放器
 */

class MvPlayerActivity : BaseActivity<ActivityMvplayerBinding>() {
    @Autowired
    @JvmField
    var id: String? = null
    private lateinit var player: ExoPlayer
    private val mViewModel by lazy {
        ViewModelProvider(this)[MvPlayerViewModel::class.java]
    }

    override fun afterCreate() {
        mBinding.mvBack.setOnClickListener {
            finish()
        }
        //id = intent.getStringExtra("id")
        player = ExoPlayer.Builder(this).build()
        mBinding.mvPlayer.player = player
        id?.let {
            mViewModel.apply {
                getMvUrl(it)
                getMvDetail(it)
                getSimiMv(it)
            }
        }
        mViewModel.apply {
            mvUrl.observe(this@MvPlayerActivity) {
                val mediaItem = MediaItem.fromUri(it.url)
                player.setMediaItem(mediaItem)
                player.prepare()
                player.playWhenReady = true
            }
            mvDetail.observe(this@MvPlayerActivity) {
                initMvDetail(it)
            }
            simiMv.observe(this@MvPlayerActivity) {
                initSimiMv(it)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        player.let {
            it.playWhenReady = true
        }
    }

    override fun onPause() {
        super.onPause()
        if (player != null) {
            player.playWhenReady = false
        }
    }
    override fun onResume() {
        super.onResume()
        player.let {
            it.playWhenReady = true
        }
    }

    override fun onStop() {
        super.onStop()
        if (player != null) {
            player.playWhenReady = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun getViewBinding(): ActivityMvplayerBinding {
        return ActivityMvplayerBinding.inflate(layoutInflater)
    }
    private fun initMvDetail(data: mData) {
        mBinding.mvTitle.text = data.name
        val mAdapter = ArtistRvAdapter()
        mAdapter.submitList(data.artists)
        mBinding.mvArtistRv.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MvPlayerActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }
    private fun initSimiMv(data: SimiMvData) {
        val mAdapter = MvListAdapter()
        mAdapter.submitList(data.mvs)
        mBinding.mvRecycler.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MvPlayerActivity)
            isNestedScrollingEnabled = false
        }
    }

}