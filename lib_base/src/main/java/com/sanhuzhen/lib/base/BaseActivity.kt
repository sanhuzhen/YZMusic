package com.sanhuzhen.lib.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
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
        cancelStatusBar()
        //注入TheRouter
        TheRouter.inject(this)
        afterCreate()
    }
    //将状态栏设置为透明，扒的掌邮
    private fun cancelStatusBar() {
        val window = this.window
        val decorView = window.decorView

        // 这是 Android 做了兼容的 Compat 包
        // 注意，使用了下面这个方法后，状态栏不会再有东西占位，
        // 可以给根布局加上 android:fitsSystemWindows=true
        // 不同布局该属性效果不同，请给合适的布局添加
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = WindowCompat.getInsetsController(window, decorView)
        // 如果你要白色的状态栏字体，请在你直接的 Activity 中单独设置 isAppearanceLightStatusBars，这里不提供方法
        windowInsetsController.isAppearanceLightStatusBars = true
        window.statusBarColor = Color.TRANSPARENT //把状态栏颜色设置成透明
    }



}