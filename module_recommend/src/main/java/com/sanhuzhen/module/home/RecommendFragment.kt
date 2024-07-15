package com.sanhuzhen.module.home

import androidx.lifecycle.ViewModelProvider
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.home.databinding.FragmentRecommendBinding


class RecommendFragment : BaseFragment<FragmentRecommendBinding>(){
    override fun getViewBinding(): FragmentRecommendBinding {
        return FragmentRecommendBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

    }

}