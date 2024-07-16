package com.example.module.search.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.module.search.ui.fragment.ArtistsFragment
import com.example.module.search.ui.fragment.MusicFragment
import com.example.module.search.ui.fragment.MvFragment

class SearchVpAdapter(fa: FragmentActivity,val l:List<Int>): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int =3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MusicFragment()
            1 -> ArtistsFragment()
            2 -> MvFragment()
            else -> error("Error Fragment")
        }
    }
}