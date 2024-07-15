package com.sanhuzhen.yzmusic.ui.activity

import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.module.login.ui.activity.activity.LoginActivity
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.yzmusic.R
import com.sanhuzhen.yzmusic.adapter.VpAdapter
import com.sanhuzhen.yzmusic.databinding.ActivityMainBinding
import com.sanhuzhen.yzmusic.ui.fragment.HotFragment
import com.sanhuzhen.yzmusic.ui.fragment.MineFragment
import com.sanhuzhen.yzmusic.ui.fragment.RecommendFragment
/**
 * @author: sanhuzhen
 * @date: 2024/7/14
 * @description:
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val fragmentList: MutableList<Fragment> = mutableListOf()
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
    override fun afterCreate() {
        mBinding.ToLogin.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
        addFragment()
        //Vp2设置
        mBinding.mainVp2.apply {
            adapter = VpAdapter(this@MainActivity)
            registerOnPageChangeCallback(object : androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mBinding.mainNav.menu.getItem(position).isChecked = true
                }
            })
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
        //禁止滑动
        mBinding.mainVp2.isUserInputEnabled = false
    }
    //添加fragment
    fun addFragment() {
        fragmentList.add(RecommendFragment())
        fragmentList.add(HotFragment())
        fragmentList.add(MineFragment())
    }
}