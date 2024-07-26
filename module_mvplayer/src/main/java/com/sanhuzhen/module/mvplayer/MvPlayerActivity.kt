package com.sanhuzhen.module.mvplayer

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.musicplayer.MusicPlayerService
import com.sanhuzhen.module.mvplayer.adapter.CommentRvAdapter
import com.sanhuzhen.module.mvplayer.adapter.SingerRvAdapter
import com.sanhuzhen.module.mvplayer.databinding.ActivityMvplayerBinding
import com.sanhuzhen.module.mvplayer.viewmodel.BaseViewModel
import com.therouter.router.Autowired
import com.therouter.router.Route
import xyz.doikki.videocontroller.StandardVideoController


/**
 * @author: sanhuzhen
 * @date: 2024/7/18
 * @description: MV播放器
 */
@Route(path = "/mvplayer/MvPlayerActivity")
class MvPlayerActivity : BaseActivity<ActivityMvplayerBinding>() {
    @Autowired
    @JvmField
    var id: String? = null
    private lateinit var mBinder: MusicPlayerService.MusicBinder
    private var isBoast = false
    val mViewModel: BaseViewModel by lazy { ViewModelProvider(this).get(BaseViewModel::class.java) }
    val mSingerAdapter: SingerRvAdapter by lazy { SingerRvAdapter() }
    val mRvAdapter: CommentRvAdapter by lazy { CommentRvAdapter() }
    var MvName: String = ""
    var MvUrl: String = ""
    var MvSinger: String = ""

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBinder = service as MusicPlayerService.MusicBinder

            if (mBinder.getPlayWhenReady()){
                mBinder.stopMusic()
                isBoast = true
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }

    }
    override fun afterCreate() {
        bindService()
        getMv()
        getback()
        getSinger()
        clickComment()
        getNum()
        clickShare()
        clickGood()
    }

    override fun getViewBinding(): ActivityMvplayerBinding {
        return ActivityMvplayerBinding.inflate(layoutInflater)
    }
    // Service绑定
    private fun bindService() {
        val intent = Intent(this, MusicPlayerService::class.java)
        startService(intent)
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    fun getMv() {
        val controller = StandardVideoController(this)
        mViewModel.getMvUrl(id!!)
        mViewModel.mMvUrl.observe(this) {
            controller.addDefaultControlComponent("MV", false)
            mBinding.player.setVideoController(controller)
            mBinding.player.setUrl(it.data.url)
            mBinding.player.setVisibilityBottom(0,0,0,0)
            mBinding.player.start()
        }
    }

    fun getSinger() {
        mViewModel.getSinger(id!!)
        mBinding.rvSinger.apply {
            adapter = mSingerAdapter
            layoutManager =
                LinearLayoutManager(this@MvPlayerActivity, LinearLayoutManager.HORIZONTAL, false)
        }
        mViewModel.mSinger.observe(this) {
            mSingerAdapter.submitList(it.data.artists)
        }
    }

    fun clickGood() {
        mBinding.mvGood.setOnClickListener {
            Toast.makeText(this, "功能开发中", Toast.LENGTH_SHORT).show()
        }
    }

    fun clickComment() {
        mBinding.mvComment.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            val bottomSheetView = layoutInflater.inflate(R.layout.comment_view, null)
            val mRv = bottomSheetView.findViewById<RecyclerView>(R.id.rv_comment)
            mRv.adapter = mRvAdapter
            mRv.layoutManager = LinearLayoutManager(this@MvPlayerActivity)
            mViewModel.getComment(id!!)
            mViewModel.mComment.observe(this) {
                mRvAdapter.submitList(it.data.comments)
            }
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }
    }

    fun getNum() {
        mViewModel.getMvDetail(id!!)
        mViewModel.mMvDetail.observe(this) {
            mBinding.tvGood.text = it.likedCount.toString()
            mBinding.tvComment.text = it.commentCount.toString()
            mBinding.tvShare.text = it.shareCount.toString()
        }
    }

    fun clickShare() {
        mBinding.mvShare.setOnClickListener {
            mViewModel.mSinger.observe(this) {
                MvName = it.data.name
                MvSinger = it.data.artists[0].name
            }
            mViewModel.mMvUrl.observe(this) {
                MvUrl = it.data.url
            }
            val shareIntent = Intent().apply {
                val title = "$MvName -- $MvSinger\n $MvUrl"
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, title)
                type = "text/plain"
            }
            // 启动分享活动
            startActivity(Intent.createChooser(shareIntent, "选择分享应用"))
        }
    }

    override fun onPause() {
        super.onPause()
        mBinding.player.pause()
    }

    override fun onResume() {
        super.onResume()
        mBinding.player.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.player.release()
        if (isBoast){
            mBinder.playMusic()
        }
    }

    override fun onBackPressed() {
        if (!mBinding.player.onBackPressed()) {
            super.onBackPressed()
        }
    }

    fun getback() {
        mBinding.mvBack.setOnClickListener {
            finish()
        }
    }
}