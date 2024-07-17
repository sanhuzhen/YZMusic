package com.example.module.login.ui.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.module.login.R
import com.example.module.login.databinding.FragmentVisitBinding
import com.example.module.login.viewmodel.MyViewModel
import com.sanhuzhen.lib.base.BaseFragment


class VisitFragment : BaseFragment<FragmentVisitBinding>(){
    private val mViewmodel: MyViewModel by lazy { ViewModelProvider(this)[MyViewModel::class.java] }
    override fun getViewBinding(): FragmentVisitBinding {
        return FragmentVisitBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        getAnswer()
    }
    private fun getAnswer() {
        mBinding.btnVisit.setOnClickListener {
            mViewmodel.getVisit()
            mViewmodel._num.observe(this) {
                if (it.code == 200) {
                    Toast.makeText(this.requireContext(), "登录成功", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this.requireContext(), "账号或密码错误", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}