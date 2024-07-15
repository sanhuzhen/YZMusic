package com.sanhuzhen.module.hot

import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.hot.databinding.FragmentHotBinding

class HotFragment : BaseFragment<FragmentHotBinding>(){
    override fun getViewBinding(): FragmentHotBinding {
        return FragmentHotBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

    }

}