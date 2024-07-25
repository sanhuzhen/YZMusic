package com.sanhuzhen.module.songlist.ui.fragment

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.songlist.adapter.SimiSongListAdapter
import com.sanhuzhen.module.songlist.databinding.FragmentSongBinding
import com.sanhuzhen.module.songlist.viewmodel.SingerViewModel
import com.therouter.TheRouter

class SongFragment : BaseFragment<FragmentSongBinding>() {

    private val songListId = arrayListOf<String>()

    private val singerSongAdapter by lazy {
        SimiSongListAdapter()
    }

    private val singerViewModel by lazy {
        ViewModelProvider(requireActivity())[SingerViewModel::class.java]
    }
    override fun getViewBinding(): FragmentSongBinding {
        return FragmentSongBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        singerViewModel.apply {
            singerHotSong.observe(this@SongFragment) {
                for (i in it) {
                    songListId.add(i.id.toString())
                }
                mBinding.tvSingerSongNum.text = songListId.size.toString()
                singerSongAdapter.submitList(it)
                mBinding.singerRecyclerView.apply {
                    adapter = singerSongAdapter
                    layoutManager = LinearLayoutManager(this.context)
                }
                mBinding.btSingerPlay.setOnClickListener {
                    TheRouter.build("/musicplayer/musicplayerActivity")
                        .withObject("SongList", songListId)
                        .navigation()
                }
            }
        }
    }

}