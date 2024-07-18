package com.sanhuzhen.module.mine.ui.fragment

import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.mine.databinding.FragmentMineBinding

class MineFragment :BaseFragment<FragmentMineBinding>(){
    override fun getViewBinding(): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

    }

}