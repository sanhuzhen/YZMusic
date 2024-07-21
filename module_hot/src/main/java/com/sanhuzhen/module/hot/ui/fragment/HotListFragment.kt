package com.sanhuzhen.module.hot.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.hot.R
import com.sanhuzhen.module.hot.adapter.HotListRvAdapter
import com.sanhuzhen.module.hot.databinding.FragmentHotListBinding
import com.sanhuzhen.module.hot.viewmodel.BaseViewModel

class HotListFragment : BaseFragment<FragmentHotListBinding>(){
    private val mAdapter:HotListRvAdapter by lazy { HotListRvAdapter() }
    private val mViewModel:BaseViewModel by lazy { ViewModelProvider(this).get(BaseViewModel::class.java) }
    override fun getViewBinding(): FragmentHotListBinding {
        return FragmentHotListBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        getfirst()
    }
    fun getfirst(){
        val LayoutManager=StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        mBinding.rvHotList.apply {
            adapter=mAdapter
            layoutManager=LayoutManager
        }
        mViewModel.getHotList(90)
        mViewModel.mHotList.observe(this){
            mAdapter.submitList(it.playlists)
        }
    }
}