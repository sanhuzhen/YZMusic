package com.sanhuzhen.module.songlist.ui.fragment

import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.songlist.databinding.FragmentEncyclopediaBinding

class EncyclopediaFragment : BaseFragment<FragmentEncyclopediaBinding>() {
    override fun getViewBinding(): FragmentEncyclopediaBinding {
        return FragmentEncyclopediaBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

    }

}