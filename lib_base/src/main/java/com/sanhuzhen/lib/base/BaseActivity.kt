package com.sanhuzhen.lib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


/**
 * @author: sanhuzhen
 * @date: 2024/7/14
 * @description:
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected val mBinding by lazy {
        getViewBinding()
    }

    protected abstract fun afterCreate()
    //得到ViewBinding
    protected abstract fun getViewBinding(): VB
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        afterCreate()
    }
}