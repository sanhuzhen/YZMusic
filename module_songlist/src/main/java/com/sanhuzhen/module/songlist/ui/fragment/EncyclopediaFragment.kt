package com.sanhuzhen.module.songlist.ui.fragment

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.songlist.adapter.SimiSingerAdapter
import com.sanhuzhen.module.songlist.adapter.SimiSongListAdapter
import com.sanhuzhen.module.songlist.databinding.FragmentEncyclopediaBinding
import com.sanhuzhen.module.songlist.viewmodel.SingerViewModel
import com.therouter.TheRouter

/**
 * @author: sanhuzhen
 * @date: 2024/7/24
 * @description:百科
 */
class EncyclopediaFragment : BaseFragment<FragmentEncyclopediaBinding>() {

    private val songListId = arrayListOf<String>()

    private val simiSingerAdapter by lazy {
        SimiSingerAdapter()
    }

    private val simiSongListAdapter by lazy {
        SimiSongListAdapter()
    }
    private val mViewModel by lazy {
        ViewModelProvider(requireActivity())[SingerViewModel::class.java]
    }

    override fun getViewBinding(): FragmentEncyclopediaBinding {
        return FragmentEncyclopediaBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        initNet()
        initView()
    }

    //初始化View
    private fun initView() {
        mBinding.btnEncyclopediaSong.setOnClickListener {
            TheRouter.build("/musicplayer/musicplayerActivity")
                .withObject("SongList", songListId)
                .navigation()
        }
    }

    //网络请求
    private fun initNet() {
        mViewModel.apply {
            singerDes.observe(this@EncyclopediaFragment) {
                mBinding.tvEncyclopediaIntroductionContent.text = it.briefDesc
            }
            simiSinger.observe(this@EncyclopediaFragment) {
                simiSingerAdapter.submitList(it)
                mBinding.rvEncyclopediaSinger.apply {
                    adapter = simiSingerAdapter
                    layoutManager = LinearLayoutManager(
                        requireActivity(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                }
            }
            singerHotSong.observe(this@EncyclopediaFragment) {
                for (i in it) {
                    songListId.add(i.id.toString())
                }
                simiSongListAdapter.submitList(it.take(12))
                mBinding.rvEncyclopediaSong.apply {
                    adapter = simiSongListAdapter
                    layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
                }
            }
        }
    }

}