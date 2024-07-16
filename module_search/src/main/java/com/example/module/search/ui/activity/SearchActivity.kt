package com.example.module.search.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.module.search.R
import com.example.module.search.adapter.SearchVpAdapter
import com.example.module.search.databinding.ActivitySearchBinding
import com.example.module.search.ui.fragment.ArtistsFragment
import com.example.module.search.viewmodel.ArtistsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.sanhuzhen.lib.base.BaseActivity

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    private val vpAdapter: SearchVpAdapter by lazy { SearchVpAdapter(this, listOf(1,2,3)) }
    private val aRvAdapter: ArtistsFragment by lazy { ArtistsFragment() }
    private val aViewmodel: ViewModel by lazy { ViewModelProvider(this)[ArtistsViewModel::class.java] }
    override fun afterCreate() {
        var keywords:String?=null
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
       }
        val fragmentArtists=supportFragmentManager.findFragmentById(R.id.fragmentArtists) as? ArtistsFragment
            mBinding.searchBtn.setOnClickListener{
                keywords=mBinding.searchEdit.text.toString()
                fragmentArtists?.updateFragmentUI()
        }
    }
    override fun getViewBinding(): ActivitySearchBinding {
        return ActivitySearchBinding.inflate(layoutInflater)
    }
}
