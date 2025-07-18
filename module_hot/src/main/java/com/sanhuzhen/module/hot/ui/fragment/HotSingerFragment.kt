package com.sanhuzhen.module.hot.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.hot.R
import com.sanhuzhen.module.hot.adapter.SingerRvAdapter
import com.sanhuzhen.module.hot.databinding.FragmentHotSingerBinding
import com.sanhuzhen.module.hot.viewmodel.BaseViewModel


class HotSingerFragment :BaseFragment<FragmentHotSingerBinding>(){
    var startX = 0f
    var startY = 0f

    private val touchSlop: Int by lazy {
        ViewConfiguration.get(requireContext()).scaledTouchSlop
    }
    val mViewModel:BaseViewModel by lazy {ViewModelProvider(this)[BaseViewModel::class.java]}
    val mRvAdapter: SingerRvAdapter by lazy { SingerRvAdapter() }
    override fun getViewBinding(): FragmentHotSingerBinding {
        return FragmentHotSingerBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        mBinding.pbLoading.visibility=View.VISIBLE
        mBinding.pbLoading.progress=0
        getData()
    }
    fun getData(){
        mBinding.rvSinger.apply {
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
            adapter=mRvAdapter
            layoutManager= LinearLayoutManager(this@HotSingerFragment.context)
        }
        mViewModel.getSinger()
        mViewModel.mSinger.observe(viewLifecycleOwner){
            mRvAdapter.submitList(it.list.artists)
            mBinding.pbLoading.progress=100
            mBinding.pbLoading.visibility=View.GONE
        }

    }

}