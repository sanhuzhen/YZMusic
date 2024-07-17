package com.sanhuzhen.module.home

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.home.adapter.BannerAdapter
import com.sanhuzhen.module.home.adapter.HomePlayListAdapter
import com.sanhuzhen.module.home.adapter.SongListAdapter
import com.sanhuzhen.module.home.bean.HomeData
import com.sanhuzhen.module.home.bean.Resource
import com.sanhuzhen.module.home.bean.SongData
import com.sanhuzhen.module.home.databinding.FragmentRecommendBinding
import com.sanhuzhen.module.home.helper.ZoomOutPageTransformer
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
    private var BannerFargments: MutableList<Fragment> = mutableListOf()
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
                for (j in i.extInfo.banners) {
                    val fragment = BannerFragment(j.pic, j.url)
                    if (!BannerFargments.contains(fragment)) {
                        BannerFargments.add(fragment)
                    }
                }
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
        mBinding.bannerVp2.apply {
            adapter = BannerAdapter(requireActivity(), BannerFargments)
            setPageTransformer(ZoomOutPageTransformer())
        }
        timer = Timer()
        timerTask = object : TimerTask() {
            override fun run() {
                //轮播
                mBinding.bannerVp2.post {
                    val currentItem = mBinding.bannerVp2.currentItem
                    val nextItem =
                        if (currentItem == BannerFargments.size - 1) 0 else currentItem + 1
                    mBinding.bannerVp2.setCurrentItem(nextItem, true)
                }
            }
        }
        timer?.schedule(timerTask, delayTime, delayTime)
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
            .navigation()
        Log.d("onClick","sakcdheiuwshfuc")
    }

}