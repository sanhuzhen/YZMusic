package com.sanhuzhen.module.hot.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sanhuzhen.module.hot.ui.fragment.HotListFragment
import com.sanhuzhen.module.hot.ui.fragment.HotSingerFragment
import com.sanhuzhen.module.hot.ui.fragment.TopListFragment

class vpAdapter(fa : Fragment,val l:List<Int>):FragmentStateAdapter(fa) {
    override fun getItemCount(): Int =3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                TopListFragment()
            }
            1->{
                HotSingerFragment()
            }
            2->{
                HotListFragment()
            }

            else -> error("Error Fragment")
        }
    }

}