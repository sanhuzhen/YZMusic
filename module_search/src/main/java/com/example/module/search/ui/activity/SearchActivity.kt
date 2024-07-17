package com.example.module.search.ui.activity

import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.module.search.R
import com.example.module.search.adapter.SearchVpAdapter
import com.example.module.search.databinding.ActivitySearchBinding
import com.example.module.search.ui.fragment.ArtistsFragment
import com.example.module.search.viewmodel.ArtistsViewModel
import com.example.module.search.viewmodel.SharedVIewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.sanhuzhen.lib.base.BaseActivity

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    private val vpAdapter: SearchVpAdapter by lazy { SearchVpAdapter(this, listOf(1,2,3)) }
    private lateinit var sharedVIewModel: SharedVIewModel
    override fun afterCreate() {
        var keywords:String?=null
        sharedVIewModel=ViewModelProvider(this).get(SharedVIewModel::class.java)
        mBinding.viewPager.adapter=vpAdapter
        mBinding.tabLayout
        mBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
        TabLayoutMediator(mBinding.tabLayout,mBinding.viewPager){ tab, position->
            when(position){
               0->{ tab.text="歌曲" }
               1 ->{ tab.text="歌手" }
               2 ->{ tab.text="MV" }
           }
       }.attach()
        mBinding.searchBack.setOnClickListener{

        }

        mBinding.searchBtn.setOnClickListener {
            keywords = mBinding.searchEdit.query.toString()
            if(TextUtils.isEmpty(keywords)){
                Toast.makeText(this,"搜索不能为空",Toast.LENGTH_SHORT).show()
            }
            else {

                sharedVIewModel.setData(keywords!!)
                Log.d("SearchActivity", "afterCreate: $keywords")
            }

        }
    }
    override fun getViewBinding(): ActivitySearchBinding {
        return ActivitySearchBinding.inflate(layoutInflater)
    }
}
