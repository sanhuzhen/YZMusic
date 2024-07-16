package com.example.module.login.ui.activity.activity

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.module.login.adapter.VpAdapter
import com.example.module.login.databinding.ActivityLoginBinding
import com.example.module.login.viewmodel.MyViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.sanhuzhen.lib.base.BaseActivity

class LoginActivity : BaseActivity <ActivityLoginBinding>() {
val vpAdapter: VpAdapter by lazy { VpAdapter(this, listOf(1,2)) }

    override fun afterCreate() {
        mBinding.viewPager.adapter=VpAdapter(this, listOf(1,2))
        mBinding.tabChoice
        mBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
        TabLayoutMediator(mBinding.tabChoice,mBinding.viewPager){ tab,position->
            when(position){
                0->{
                    tab.text="手机号/邮箱注册"
                }
                1 ->{
                    tab.text="短信验证码登录"
                }
            }
        }.attach()
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }
}
