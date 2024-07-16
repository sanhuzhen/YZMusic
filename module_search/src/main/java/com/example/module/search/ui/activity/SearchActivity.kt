package com.example.module.search.ui.activity

import androidx.viewpager2.widget.ViewPager2
import com.example.module.search.adapter.SearchVpAdapter
import com.example.module.search.databinding.ActivitySearchBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.sanhuzhen.lib.base.BaseActivity

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    val vpAdapter: SearchVpAdapter by lazy { SearchVpAdapter(this, listOf(1,2,3)) }
    override fun afterCreate() {
        mBinding.viewPager.adapter=SearchVpAdapter(this, listOf(1,2,3))
        mBinding.tabLayout
        mBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
        TabLayoutMediator(mBinding.tabLayout,mBinding.viewPager){ tab, position->
            when(position){
               0->{
                   tab.text="歌曲"
               }
               1 ->{
                   tab.text="歌手"
               }
               2 ->{
                   tab.text="MV"
               }
           }
       }

    }

    override fun getViewBinding(): ActivitySearchBinding {
        return ActivitySearchBinding.inflate(layoutInflater)
    }

}