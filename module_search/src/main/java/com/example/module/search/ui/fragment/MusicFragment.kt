package com.example.module.search.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module.search.R
import com.example.module.search.adapter.MusicRvAdapter
import com.example.module.search.bean.Song
import com.example.module.search.databinding.FragmentMusicBinding
import com.example.module.search.viewmodel.MusicViewModel
import com.example.module.search.viewmodel.SharedVIewModel
import com.sanhuzhen.lib.base.BaseFragment
import com.therouter.TheRouter


class MusicFragment : BaseFragment<FragmentMusicBinding>(){
    var startX = 0f
    var startY = 0f

    private val touchSlop: Int by lazy {
        ViewConfiguration.get(requireContext()).scaledTouchSlop
    }
    private val rvAdapter: MusicRvAdapter by lazy {MusicRvAdapter() }
    private val sharedVIewModel: SharedVIewModel by lazy { ViewModelProvider(requireActivity())[SharedVIewModel::class.java] }
    private val musicViewModel: MusicViewModel by lazy { ViewModelProvider(this)[MusicViewModel::class.java] }
    private val SongList:MutableList<Song> = mutableListOf()
    private val SongLists = arrayListOf<String>()
    override fun getViewBinding(): FragmentMusicBinding {
        return FragmentMusicBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

        initRv()
        playall()
    }
    fun playall(){
        mBinding.ivAll.setOnClickListener {
            TheRouter.build("/musicplayer/musicplayerActivity")
                .withObject("SongList", SongLists)
                .navigation()
        }
    }
    fun initRv(){
        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MusicFragment.context)
            adapter = rvAdapter
            setOnTouchListener { v, event ->
                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        startX = event.x
                        startY = event.y
                        v.parent.requestDisallowInterceptTouchEvent(true)
                    }

                    MotionEvent.ACTION_MOVE -> {
                        val dx = kotlin.math.abs(event.x - startX)
                        val dy = kotlin.math.abs(event.y - startY)
                        if (dx > dy) {
                            v.parent.requestDisallowInterceptTouchEvent(false)
                        } else {
                            v.parent.requestDisallowInterceptTouchEvent(true)
                        }
                    }

                    MotionEvent.ACTION_UP -> {
                        val dx = kotlin.math.abs(event.x - startX)
                        val dy = kotlin.math.abs(event.y - startY)
                        if (dx < touchSlop && dy < touchSlop) {
                            v.performClick()
                        }
                    }
                }
                false
            }
        }
        sharedVIewModel.someData.observe(viewLifecycleOwner) {
            mBinding.pbLoading.progress = 0
            mBinding.pbLoading.visibility = View.VISIBLE
            musicViewModel.getMusicData(sharedVIewModel.someData.value!!,100)
            Log.d("MusicFragment", "onViewCreated: ${sharedVIewModel.someData.value}")
            musicViewModel.musicData.observe(viewLifecycleOwner) {
                if (it.result.songs.isNotEmpty()){
                    mBinding.pbLoading.visibility=View.GONE
                }
                SongList.addAll(it.result.songs)
                Log.d("Music", "${it}")
                rvAdapter.submitList(it.result.songs)
                mBinding.pbLoading.progress=100
                mBinding.pbLoading.visibility=View.GONE
                for (i in SongList) {
                    SongLists.add(i.id.toString())
                }
            }

        }
    }
    override fun onResume() {
        super.onResume()
        for (i in SongList) {
            SongLists.add(i.id.toString())
        }
    }
}