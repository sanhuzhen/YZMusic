package com.example.module.search.ui.fragment


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module.search.adapter.ArtistsRvAdapter
import com.example.module.search.bean.ArtistsData
import com.example.module.search.databinding.FragmentArtistsBinding
import com.example.module.search.viewmodel.ArtistsViewModel
import com.sanhuzhen.lib.base.BaseFragment


class ArtistsFragment : BaseFragment<FragmentArtistsBinding>() {
    private val mViewModel by lazy{
        ViewModelProvider(this)[ArtistsViewModel::class.java]
    }
    private val madapter= ArtistsRvAdapter()
    // Fragment中的某个方法，用于更新UI等
    fun updateFragmentUI() {
        // 更新Fragment的UI
        mBinding.recyclerViewArtists.apply {
            layoutManager= LinearLayoutManager(this@ArtistsFragment.requireContext())
            adapter= madapter
        }
        mViewModel.getArtistsData()
    }

    override fun getViewBinding(): FragmentArtistsBinding {
        return FragmentArtistsBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        updateFragmentUI()
    }
}