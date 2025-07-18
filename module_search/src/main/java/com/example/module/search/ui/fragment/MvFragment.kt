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
import com.example.module.search.adapter.MvRvAdapter
import com.example.module.search.databinding.FragmentMvBinding
import com.example.module.search.viewmodel.MvViewModel
import com.example.module.search.viewmodel.SharedVIewModel
import com.sanhuzhen.lib.base.BaseFragment

class MvFragment : BaseFragment<FragmentMvBinding>(){
    var startX = 0f
    var startY = 0f

    private val touchSlop: Int by lazy {
        ViewConfiguration.get(requireContext()).scaledTouchSlop
    }
    private val sharedVIewModel: SharedVIewModel by lazy { ViewModelProvider(requireActivity())[SharedVIewModel::class.java] }
    private val mvViewModel: MvViewModel by lazy { ViewModelProvider(this)[MvViewModel::class.java] }
    private val mvAdapter: MvRvAdapter by lazy { MvRvAdapter() }
    override fun getViewBinding(): FragmentMvBinding {
        return FragmentMvBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

        initRv()
    }
    fun initRv(){
        mBinding.rvMv.apply {
            layoutManager = LinearLayoutManager(this@MvFragment.context)
            adapter = mvAdapter
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
            mBinding.pbLoading.visibility = View.VISIBLE
            mvViewModel.getMvData(sharedVIewModel.someData.value!!,1004,100)
            Log.d("MvFragment", "onViewCreated: ${sharedVIewModel.someData.value}")
            mvViewModel.mvData.observe(viewLifecycleOwner) {
               if (it.result.mvs.isNotEmpty()){
                   mBinding.pbLoading.visibility=View.GONE
               }
                mvAdapter.submitList(it.result.mvs)
                mBinding.pbLoading.progress=100
                mBinding.pbLoading.visibility=View.GONE
            }
        }
    }

}