package com.sanhuzhen.module.mine.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        mBinding.pbLoading.visibility= View.VISIBLE
        mBinding.pbLoading.progress=0
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
            mViewModel.mFavourite.observe(this){
                if (it.playlist.tracks.isNotEmpty()){
                    TheRouter.build("/musicplayer/musicplayerActivity").withObject("SongList", SongLists)
                        .navigation()
                }
                else {
                    Toast.makeText(this,"你还没有收藏歌曲",Toast.LENGTH_SHORT).show()
                }
            }
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
                SongList.addAll(it.playlist.tracks)
                rvAdapter.submitList(it.playlist.tracks)
                mBinding.pbLoading.progress=100
                mBinding.pbLoading.visibility= View.GONE
                for (i in SongList) {
                    SongLists.add(i.id.toString())
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        for (i in SongList) {
            SongLists.add(i.id.toString())
        }
    }
}