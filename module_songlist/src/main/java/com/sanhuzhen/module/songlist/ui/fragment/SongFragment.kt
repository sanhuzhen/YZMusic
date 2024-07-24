package com.sanhuzhen.module.songlist.ui.fragment

import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.songlist.databinding.FragmentSongBinding

class SongFragment : BaseFragment<FragmentSongBinding>() {
    override fun getViewBinding(): FragmentSongBinding {
        return FragmentSongBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

    }

}