package com.sanhuzhen.module.musicplayer.fragment

import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.musicplayer.databinding.FragmentWordBinding

class WordFragment: BaseFragment<FragmentWordBinding>() {
    override fun getViewBinding(): FragmentWordBinding {
        return FragmentWordBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

    }

}