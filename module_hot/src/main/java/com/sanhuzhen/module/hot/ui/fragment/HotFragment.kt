package com.sanhuzhen.module.hot.ui.fragment

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.hot.adapter.vpAdapter
import com.sanhuzhen.module.hot.databinding.FragmentHotBinding

class HotFragment : BaseFragment<FragmentHotBinding>(){
    val vpAdapter: vpAdapter by lazy { vpAdapter(this, listOf(1,2,3)) }
    override fun getViewBinding(): FragmentHotBinding {
        return FragmentHotBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        getFirst()
    }
    fun getFirst(){
        mBinding.vpList.adapter=vpAdapter
        mBinding.tabChoice
        mBinding.vpList.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
        TabLayoutMediator(mBinding.tabChoice,mBinding.vpList){ tab,position->
            when(position){
                0->{
                    tab.text="歌榜"
                }
                1 ->{
                    tab.text="热门歌手"
                }
                2->{
                    tab.text="热门歌单"
                }
            }
        }.attach()
    }

}