package com.sanhuzhen.yzmusic.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sanhuzhen.yzmusic.ui.fragment.HotFragment
import com.sanhuzhen.yzmusic.ui.fragment.MineFragment
import com.sanhuzhen.yzmusic.ui.fragment.RecommendFragment


class VpAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa)  {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> RecommendFragment()
            1 -> HotFragment()
            2 -> MineFragment()
            else -> error("Error Fragment")
        }
    }

}