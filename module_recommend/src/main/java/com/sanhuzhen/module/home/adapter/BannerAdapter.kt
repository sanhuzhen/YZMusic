package com.sanhuzhen.module.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sanhuzhen.module.home.bean.Banner

class BannerAdapter(fa: FragmentActivity,val bannerFragments: MutableList<Fragment>): FragmentStateAdapter(fa){
    override fun getItemCount(): Int {
        //返回一个很大的值，以实现无限轮播的效果
        return Int.MAX_VALUE
    }

    override fun createFragment(position: Int) :Fragment {
        //当position大于等于bannerFragments.size时，取模
        return bannerFragments[position % bannerFragments.size]
    }


}