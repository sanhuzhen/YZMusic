package com.sanhuzhen.module.hot.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
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
    var startX = 0f
    var startY = 0f

    private val touchSlop: Int by lazy {
        ViewConfiguration.get(requireContext()).scaledTouchSlop
    }
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
        val LayoutManager= GridLayoutManager(this.requireContext(),2)
        mBinding.rvTopList.apply {
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
            adapter=mAdapter
            layoutManager=LayoutManager
        }
        mViewModel.getTopList()
        mViewModel.mTopList.observe(viewLifecycleOwner){
            mAdapter.submitList(it.list)
            mBinding.pbLoading.progress=100
            mBinding.pbLoading.visibility=View.GONE
        }

    }
}