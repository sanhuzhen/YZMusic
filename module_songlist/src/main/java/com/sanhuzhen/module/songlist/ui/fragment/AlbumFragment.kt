package com.sanhuzhen.module.songlist.ui.fragment

import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.songlist.databinding.FragmentAlbumBinding

class AlbumFragment : BaseFragment<FragmentAlbumBinding>() {
    override fun getViewBinding(): FragmentAlbumBinding {
        return FragmentAlbumBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

    }
}