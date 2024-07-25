package com.sanhuzhen.module.musicplayer.fragment

import androidx.lifecycle.ViewModelProvider
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.musicplayer.databinding.FragmentWordBinding
import com.sanhuzhen.module.musicplayer.helper.DealWordHelper
import com.sanhuzhen.module.musicplayer.viewmodel.PlayViewModel

class WordFragment: BaseFragment<FragmentWordBinding>() {

    private val mViewModel by lazy {
        ViewModelProvider(requireActivity())[PlayViewModel::class.java]
    }

    override fun getViewBinding(): FragmentWordBinding {
        return FragmentWordBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        mViewModel.songLyric.observe(this){
            if (it.isNotEmpty()){
                 mBinding.tvWord.text = DealWordHelper.dealWord(it)
            }
        }
    }

}