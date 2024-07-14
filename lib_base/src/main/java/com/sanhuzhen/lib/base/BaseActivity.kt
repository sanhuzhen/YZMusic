package com.sanhuzhen.lib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.therouter.TheRouter

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
        //注入TheRouter
        TheRouter.inject(this)
        afterCreate()
    }
}