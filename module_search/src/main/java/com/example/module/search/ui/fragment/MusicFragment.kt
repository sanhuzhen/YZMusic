package com.example.module.search.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module.search.R
import com.example.module.search.adapter.MusicRvAdapter
import com.example.module.search.databinding.FragmentMusicBinding
import com.example.module.search.viewmodel.MusicViewModel
import com.example.module.search.viewmodel.SharedVIewModel
import com.sanhuzhen.lib.base.BaseFragment


class MusicFragment : BaseFragment<FragmentMusicBinding>(){
    private val rvAdapter: MusicRvAdapter by lazy {MusicRvAdapter() }
    private val sharedVIewModel: SharedVIewModel by lazy { ViewModelProvider(requireActivity())[SharedVIewModel::class.java] }
    private val musicViewModel: MusicViewModel by lazy { ViewModelProvider(this)[MusicViewModel::class.java] }
    override fun getViewBinding(): FragmentMusicBinding {
        return FragmentMusicBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        initRv()
    }
    fun initRv(){
        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MusicFragment.context)
            adapter = rvAdapter
        }
        sharedVIewModel.someData.observe(viewLifecycleOwner) {
            musicViewModel.getMusicData(sharedVIewModel.someData.value!!,100)
            Log.d("MusicFragment", "onViewCreated: ${sharedVIewModel.someData.value}")
            musicViewModel.musicData.observe(viewLifecycleOwner) {
                Log.d("Music", "${it}")
                rvAdapter.submitList(it.result.songs)
            }

        }
    }

}