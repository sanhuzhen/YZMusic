package com.sanhuzhen.module.mine.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.mine.R
import com.sanhuzhen.module.mine.adapter.FavouriteRvAdapter
import com.sanhuzhen.module.mine.bean.Track
import com.sanhuzhen.module.mine.databinding.ActivityFavouriteBinding
import com.sanhuzhen.module.mine.viewmodel.BaseViewModel
import com.therouter.TheRouter

class FavouriteActivity : BaseActivity<ActivityFavouriteBinding>(){
    private var MyId :Long?=null
    private val mViewModel: BaseViewModel by lazy { ViewModelProvider(this)[BaseViewModel::class.java] }
    private val rvAdapter: FavouriteRvAdapter by lazy { FavouriteRvAdapter() }
    private val SongList:MutableList<Track> = mutableListOf()
    private val SongLists = arrayListOf<String>()
    override fun afterCreate() {
        getsp()
        getitem()
        getback()
        playall()
    }

    override fun getViewBinding(): ActivityFavouriteBinding {
        return ActivityFavouriteBinding.inflate(layoutInflater)
    }
    fun getsp(){
        val sp=this.getSharedPreferences("user", MODE_PRIVATE)
        MyId=sp.getLong("id",0)
    }
    fun getback(){
        mBinding.ivBack.setOnClickListener {
            finish()
        }
    }
    fun playall(){
        mBinding.ivAll.setOnClickListener {
            TheRouter.build("/musicplayer/musicplayerActivity").withObject("SongList", SongLists)
                .navigation()
        }
    }
    fun getitem(){
        mBinding.rvFavourite.apply {
            adapter=rvAdapter
            layoutManager= LinearLayoutManager(this@FavouriteActivity)
        }
        mViewModel.getPL(MyId!!)
        mViewModel.mPL.observe(this){
            mViewModel.getFavourite(it.playlist[0].id)
            mViewModel.mFavourite.observe(this){
                rvAdapter.submitList(it.playlist.tracks)
                for (i in SongList) {
                    SongLists.add(i.id.toString())
                }
            }
        }
    }
}