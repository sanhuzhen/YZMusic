package com.sanhuzhen.module.recommend

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.recommend.adapter.BannerAdapter
import com.sanhuzhen.module.recommend.adapter.HomePlayListAdapter
import com.sanhuzhen.module.recommend.adapter.SongListAdapter
import com.sanhuzhen.module.recommend.bean.HomeData
import com.sanhuzhen.module.recommend.bean.Resource
import com.sanhuzhen.module.recommend.bean.SongData
import com.sanhuzhen.module.home.databinding.FragmentRecommendBinding
import com.sanhuzhen.module.recommend.bean.Banner
import com.sanhuzhen.module.recommend.helper.ZoomOutPageTransformer
import com.therouter.TheRouter
import java.util.Timer
import java.util.TimerTask

/**
 * @author: sanhuzhen
 * @date: 2024/7/16
 * @description:
 */
class RecommendFragment : BaseFragment<FragmentRecommendBinding>(),
    HomePlayListAdapter.OnItemClickListener {
    private var BannerList: MutableList<Banner> = mutableListOf()
    private var homePageSlidePlayList: MutableList<Resource> = mutableListOf()
    private var homePageSlidePlayListTitle: String? = null
    private var SongList: MutableList<SongData> = mutableListOf()
    private var SongListTitle: String? = null
    private var MySongList: MutableList<Resource> = mutableListOf()
    private var MySongListTitle: String? = null

    //自动轮播
    private var timer: Timer? = null
    private var timerTask: TimerTask? = null

    //轮播间隔时间
    private val delayTime = 6000L
    private val mViewModel by lazy {
        ViewModelProvider(this)[RecommendViewModel::class.java]
    }

    override fun getViewBinding(): FragmentRecommendBinding {
        return FragmentRecommendBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        mViewModel.homeData.observe(this) {
            if (it.code == 200) {
                otherEvent(it)
                //UI控件的初始化事件
                initEvents()
            } else {
                Toast.makeText(requireContext(), "请检查网络是否连接", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun otherEvent(data: HomeData) {
        //得到HomeData中的Banner数据，并将Fragment加入BannerFragments
        for (i in data.data.blocks) {
            if (i.blockCode == "HOMEPAGE_BANNER") {
                BannerList = i.extInfo.banners.toMutableList()
            } else if (i.blockCode == "HOMEPAGE_BLOCK_PLAYLIST_RCMD") {
                homePageSlidePlayListTitle = i.uiElement.subTitle.title
                for (j in i.creatives) {
                    if (j.creativeType == "scroll_playlist" || j.creativeType == "list") {
                        for (z in j.resources) {
                            homePageSlidePlayList.add(z)
                        }
                    }
                }
            } else if (i.blockCode == "HOMEPAGE_BLOCK_STYLE_RCMD") {
                SongListTitle = i.uiElement.subTitle.title
                for (j in i.creatives) {
                    for (z in j.resources) {
                        SongList.add(z.resourceExtInfo.songData)
                    }
                }
            } else if (i.blockCode == "HOMEPAGE_BLOCK_MGC_PLAYLIST") {
                MySongListTitle = i.uiElement.subTitle.title
                for (j in i.creatives) {
                    for (z in j.resources) {
                        MySongList.add(z)
                    }
                }
            }
        }
    }

    private fun initEvents() {
        initBanner()
        initHomeList()
        initSongList()
        initMyList()
    }

    private fun initMyList() {
        mBinding.recommendMySongListTitle.text = MySongListTitle
        val mAdapter = HomePlayListAdapter(requireActivity(),this)
        mAdapter.submitList(MySongList)
        mBinding.recommendMySongListRv.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            this.adapter = mAdapter
        }
    }

    private fun initSongList() {
        mBinding.recommendSongListTitle.text = SongListTitle
        val mAdapter = SongListAdapter()
        mAdapter.submitList(SongList)
        mBinding.recommendSongListRv.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
            adapter = mAdapter

        }

    }

    //实现HomePlayList
    private fun initHomeList() {
        mBinding.recommendPageTitle.text = homePageSlidePlayListTitle
        val adapter = HomePlayListAdapter(requireActivity(), this)
        adapter.submitList(homePageSlidePlayList)
        mBinding.recommendRv.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }
    }

    //实现Banner
    private fun initBanner() {
        val mAdapter = BannerAdapter()
        mAdapter.submitList(BannerList)
        mBinding.bannerVp2.apply {
            adapter = mAdapter
            setPageTransformer(ZoomOutPageTransformer())
        }
        killDelayedTask()
        timer = Timer()
        timerTask = object : TimerTask() {
            override fun run() {
                //轮播
                mBinding.bannerVp2.post {
                    val currentItem = mBinding.bannerVp2.currentItem
                    val nextItem =
                        if (currentItem == BannerList.size - 1) 0 else currentItem + 1
                    mBinding.bannerVp2.setCurrentItem(nextItem, true)
                }
            }
        }
        timer?.schedule(timerTask, delayTime, delayTime)
    }
    //销毁定时器,保证只有一组定时任务在运行
    private fun killDelayedTask() {
        if (timer != null) {
            timer?.cancel()
            timer = null
        }
        if (timerTask != null) {
            timerTask?.cancel()
            timerTask = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("RecommendFragment", "onDestoryView")
        timer?.cancel()
        timerTask = null
        timer = null
    }

    //设置HomePageLists中的点击事件
    override fun onItemClick(item: Resource) {
        TheRouter.build("/songlist/songListActivity")
            .withString("id", item.resourceId)
            .withString("alTv",item.uiElement.image.imageUrl)

            .navigation()
        Log.d("onClick","sakcdheiuwshfuc")
    }

}