package com.sanhuzhen.module.hot.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.hot.R
import com.sanhuzhen.module.hot.adapter.SongRvAdapter
import com.sanhuzhen.module.hot.databinding.ActivityDetailBinding
import com.sanhuzhen.module.hot.viewmodel.BaseViewModel

class DetailActivity :BaseActivity<ActivityDetailBinding>(){
    private val mViewModel: BaseViewModel by lazy { ViewModelProvider(this)[BaseViewModel::class.java]}
    private val mRvAdapter: SongRvAdapter by lazy { SongRvAdapter() }
    override fun afterCreate() {

        getfirst()
    }

    override fun getViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }
    fun getfirst(){
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        mBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
        mBinding.collapsingToolbar.title=intent.getStringExtra("name")
        mBinding.recyclerView.apply {
            adapter=mRvAdapter
            layoutManager=LinearLayoutManager(this@DetailActivity)
        }
        val FromTOP=intent
        val Id=FromTOP.getLongExtra("id",0)
        val Img=FromTOP.getStringExtra("img")
        if (Img!=null){
            Glide.with(this).load(Img)
                .transform(CenterCrop(), RoundedCorners(30)).into(mBinding.image)
        }
        mViewModel.getSongList(Id)
        mViewModel.mSongList.observe(this){
            mRvAdapter.submitList(it.playlist.tracks)
        }
    }

}