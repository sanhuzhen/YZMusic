package com.sanhuzhen.module.hot.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.hot.R
import com.sanhuzhen.module.hot.adapter.TopListRvAdapter
import com.sanhuzhen.module.hot.databinding.FragmentTopListBinding
import com.sanhuzhen.module.hot.viewmodel.BaseViewModel

class TopListFragment : BaseFragment<FragmentTopListBinding>() {
    private val mViewModel :BaseViewModel by lazy {ViewModelProvider(this).get(BaseViewModel::class.java)}
    private val mAdapter:TopListRvAdapter by lazy { TopListRvAdapter() }
    override fun getViewBinding(): FragmentTopListBinding {
        return FragmentTopListBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        mBinding.pbLoading.progress=0
        mBinding.pbLoading.visibility=View.VISIBLE
        getFirst()
    }
    fun getFirst(){
        mBinding.rvTopList.adapter=mAdapter
        val layoutManager= GridLayoutManager(this.requireContext(),2)
        mBinding.rvTopList.layoutManager=layoutManager
        mViewModel.getTopList()
        mViewModel.mTopList.observe(viewLifecycleOwner){
            mAdapter.submitList(it.list)
            mBinding.pbLoading.progress=100
            mBinding.pbLoading.visibility=View.GONE
        }

    }
}