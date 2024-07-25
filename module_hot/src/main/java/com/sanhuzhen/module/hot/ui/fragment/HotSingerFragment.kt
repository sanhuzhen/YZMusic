package com.sanhuzhen.module.hot.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.hot.R
import com.sanhuzhen.module.hot.adapter.SingerRvAdapter
import com.sanhuzhen.module.hot.databinding.FragmentHotSingerBinding
import com.sanhuzhen.module.hot.viewmodel.BaseViewModel


class HotSingerFragment :BaseFragment<FragmentHotSingerBinding>(){
    val mViewModel:BaseViewModel by lazy {ViewModelProvider(this)[BaseViewModel::class.java]}
    val mRvAdapter: SingerRvAdapter by lazy { SingerRvAdapter() }
    override fun getViewBinding(): FragmentHotSingerBinding {
        return FragmentHotSingerBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        getData()
    }
    fun getData(){
        mBinding.rvSinger.apply {
            adapter=mRvAdapter
            layoutManager= LinearLayoutManager(this@HotSingerFragment.context)
        }
        mViewModel.getSinger()
        mViewModel.mSinger.observe(viewLifecycleOwner){
            mRvAdapter.submitList(it.list.artists)
        }

    }

}