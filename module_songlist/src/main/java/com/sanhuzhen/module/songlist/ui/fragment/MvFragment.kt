package com.sanhuzhen.module.songlist.ui.fragment

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanhuzhen.lib.base.BaseFragment
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
            if (it.isEmpty()){
                Toast.makeText(this.context,"这个人很神秘，\n他什么都没有留下",Toast.LENGTH_SHORT).show()
            }
        }
    }

}