package com.sanhuzhen.yzmusic.ui.fragment

import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.yzmusic.databinding.FragmentRemmendBinding

class RecommendFragment : BaseFragment<FragmentRemmendBinding>() {
    override fun getViewBinding(): FragmentRemmendBinding {
        return FragmentRemmendBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

    }
}