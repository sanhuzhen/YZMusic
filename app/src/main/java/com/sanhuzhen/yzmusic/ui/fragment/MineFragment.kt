package com.sanhuzhen.yzmusic.ui.fragment

import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.yzmusic.databinding.FragmentMineBinding

class MineFragment : BaseFragment<FragmentMineBinding>() {
    override fun getViewBinding(): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

    }
}