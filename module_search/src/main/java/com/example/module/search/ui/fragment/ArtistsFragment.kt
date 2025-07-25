package com.example.module.search.ui.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module.search.R
import com.example.module.search.adapter.ArtistsRvAdapter
import com.example.module.search.bean.ArtistsData
import com.example.module.search.databinding.FragmentArtistsBinding
import com.example.module.search.viewmodel.ArtistsViewModel
import com.example.module.search.viewmodel.SharedVIewModel
import com.sanhuzhen.lib.base.BaseFragment


class ArtistsFragment : Fragment(){
    var startX = 0f
    var startY = 0f

    private val touchSlop: Int by lazy {
        ViewConfiguration.get(requireContext()).scaledTouchSlop
    }
    private val mBinding: FragmentArtistsBinding by lazy { FragmentArtistsBinding.inflate(layoutInflater) }
    private val rvAdapter: ArtistsRvAdapter by lazy { ArtistsRvAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedVIewModel: SharedVIewModel by lazy { ViewModelProvider(requireActivity())[SharedVIewModel::class.java] }
        val artistsViewModel: ArtistsViewModel by lazy { ViewModelProvider(this)[ArtistsViewModel::class.java] }
        mBinding.recyclerViewArtists.apply {setOnTouchListener { v, event ->
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
            layoutManager = LinearLayoutManager(this@ArtistsFragment.context)
            adapter = rvAdapter
        }
       sharedVIewModel.someData.observe(viewLifecycleOwner) {
           mBinding.pbLoading.visibility=View.VISIBLE
           artistsViewModel.getArtistsData(sharedVIewModel.someData.value!!, 100)
           artistsViewModel.artistsData.observe(viewLifecycleOwner) {
               mBinding.pbLoading.progress=0
               if (it.result.artists.isNotEmpty()){
                   mBinding.pbLoading.visibility=View.GONE
               }
               rvAdapter.submitList(it.result.artists)
               mBinding.pbLoading.progress=100
               mBinding.pbLoading.visibility=View.GONE
               Log.d("ArtistsFragment", "onViewCreated: ${it.result.artists}")
           }
           Log.d("ArtistsFragment", "onViewCreated: ${sharedVIewModel.someData.value}")

        }

   }
}


