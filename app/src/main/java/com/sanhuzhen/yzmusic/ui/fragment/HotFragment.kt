package com.sanhuzhen.yzmusic.ui.fragment

import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.yzmusic.databinding.FragmentHotBinding

class HotFragment : BaseFragment<FragmentHotBinding>() {
    override fun getViewBinding(): FragmentHotBinding {
        return FragmentHotBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

    }
}