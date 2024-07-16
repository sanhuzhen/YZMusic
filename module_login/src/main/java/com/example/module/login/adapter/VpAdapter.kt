package com.example.module.login.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.module.login.ui.activity.fragment.LoginByFragment
import com.example.module.login.ui.activity.fragment.LoginFragment

class VpAdapter(fa: FragmentActivity, val l: List<Int>):FragmentStateAdapter(fa) {
    override fun getItemCount(): Int =2

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0->{
                LoginFragment()
            }
            1->{
                LoginByFragment()
            }

            else -> error("Error Fragment")
        }
    }
}