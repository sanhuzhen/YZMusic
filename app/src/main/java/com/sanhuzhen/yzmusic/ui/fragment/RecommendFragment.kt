package com.sanhuzhen.yzmusic.ui.fragment

import androidx.lifecycle.ViewModelProvider
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.yzmusic.databinding.FragmentRemmendBinding
import com.sanhuzhen.yzmusic.viewmodel.RecommendViewModel

class RecommendFragment : BaseFragment<FragmentRemmendBinding>() {

    private val mViewModel by lazy{
        ViewModelProvider(requireActivity())[RecommendViewModel::class.java]
    }
    override fun getViewBinding(): FragmentRemmendBinding {
        return FragmentRemmendBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        mViewModel.getBlocks()
    }
}