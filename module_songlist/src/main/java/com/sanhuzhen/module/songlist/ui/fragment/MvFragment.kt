package com.sanhuzhen.module.songlist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.songlist.R
import com.sanhuzhen.module.songlist.adapter.MvAdapter
import com.sanhuzhen.module.songlist.databinding.FragmentMvBinding
import com.sanhuzhen.module.songlist.viewmodel.SingerViewModel

class MvFragment : BaseFragment<FragmentMvBinding>() {

    private val mViewModel by lazy {
        ViewModelProvider(requireActivity())[SingerViewModel::class.java]
    }

    private val mvAdapter by lazy {
        MvAdapter()
    }
    override fun getViewBinding(): FragmentMvBinding {
        return FragmentMvBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        mViewModel.singerMv.observe(this){
            mvAdapter.submitList(it)
            mBinding.rvMv.apply {
                adapter = mvAdapter
                layoutManager = LinearLayoutManager(this.context)
            }
        }
    }

}