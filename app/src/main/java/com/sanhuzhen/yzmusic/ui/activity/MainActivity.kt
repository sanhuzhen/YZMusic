package com.sanhuzhen.yzmusic.ui.activity

import androidx.core.view.GravityCompat
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.yzmusic.R
import com.sanhuzhen.yzmusic.adapter.VpAdapter
import com.sanhuzhen.yzmusic.databinding.ActivityMainBinding
import com.therouter.TheRouter

/**
 * @author: sanhuzhen
 * @date: 2024/7/14
 * @description:
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
    override fun afterCreate() {
        //底部导航栏
        initBottomNav()
        //抽屉式菜单
        initNavigationView()
        initEvent()
    }
    private fun initBottomNav(){
        //Vp2设置
        mBinding.mainVp2.apply {
            adapter = VpAdapter(this@MainActivity)
            registerOnPageChangeCallback(object : androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mBinding.mainNav.menu.getItem(position).isChecked = true
                }
            })
            //禁止滑动
            isUserInputEnabled = false
        }
        //Nav设置
        mBinding.mainNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_nav_home -> mBinding.mainVp2.currentItem = 0
                R.id.menu_nav_hot -> mBinding.mainVp2.currentItem = 1
                R.id.menu_nav_mine -> mBinding.mainVp2.currentItem = 2
            }
            true
        }
    }
    private fun initNavigationView(){
        mBinding.mainToolbarMenu.setOnClickListener {
            mBinding.mainDrawer.openDrawer(GravityCompat.START)
        }
        mBinding.mainNavView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_nav_home -> mBinding.mainVp2.currentItem = 0
                R.id.menu_nav_hot -> mBinding.mainVp2.currentItem = 1
                R.id.menu_nav_mine -> mBinding.mainVp2.currentItem = 2
            }
            true
        }
    }
    private fun initEvent(){
        mBinding.mainToolbarSearch.setOnClickListener {
            TheRouter.build("/search/SearchActivity").navigation()
        }
    }
}