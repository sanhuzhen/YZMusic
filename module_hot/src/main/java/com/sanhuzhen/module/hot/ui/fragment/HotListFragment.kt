package com.sanhuzhen.module.hot.ui.fragment


import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.hot.adapter.HotListRvAdapter
import com.sanhuzhen.module.hot.databinding.FragmentHotListBinding
import com.sanhuzhen.module.hot.viewmodel.BaseViewModel

class HotListFragment : BaseFragment<FragmentHotListBinding>() {

    var startX = 0f
    var startY = 0f

    private val touchSlop: Int by lazy {
        ViewConfiguration.get(requireContext()).scaledTouchSlop
    }

    private val mAdapter: HotListRvAdapter by lazy { HotListRvAdapter() }
    private val mViewModel: BaseViewModel by lazy { ViewModelProvider(this).get(BaseViewModel::class.java) }

    override fun getViewBinding(): FragmentHotListBinding {
        return FragmentHotListBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        mBinding.pbLoading.progress = 0
        mBinding.pbLoading.visibility = View.VISIBLE
        getfirst()
    }

    fun getfirst() {
        val LayoutManager = GridLayoutManager(this.requireContext(), 3)
        mBinding.rvHotList.apply {
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
            adapter = mAdapter
            layoutManager = LayoutManager
        }

        // 请求热门列表数据
        mViewModel.getHotList(90)
        mViewModel.mHotList.observe(viewLifecycleOwner) {
            mAdapter.submitList(it.playlists)
            mBinding.pbLoading.progress = 100
            mBinding.pbLoading.visibility = View.GONE
        }
    }
}