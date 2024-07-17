package com.sanhuzhen.lib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.therouter.TheRouter

/**
 * @author: sanhuzhen
 * @date: 2024/7/14
 * @description:
 */
abstract class BaseFragment<VB: ViewBinding> : Fragment() {

    abstract fun getViewBinding(): VB
    abstract fun afterCreate()
    protected val mBinding by lazy {
        getViewBinding()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TheRouter.inject(this)
        afterCreate()
    }
}