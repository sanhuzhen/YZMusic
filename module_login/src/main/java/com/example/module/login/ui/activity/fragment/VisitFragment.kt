package com.example.module.login.ui.activity.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.module.login.R
import com.example.module.login.databinding.FragmentVisitBinding
import com.example.module.login.manager.UserManager
import com.example.module.login.viewmodel.MyViewModel
import com.sanhuzhen.lib.base.BaseFragment


class VisitFragment : BaseFragment<FragmentVisitBinding>(){
    private val mViewmodel: MyViewModel by lazy { ViewModelProvider(this)[MyViewModel::class.java] }
    override fun getViewBinding(): FragmentVisitBinding {
        return FragmentVisitBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        UserManager.initialize(requireContext())
        getAnswer()
        getknown()
    }
    private fun getAnswer() {
        mBinding.btnVisit.setOnClickListener {
            mViewmodel.getVisit()
            mViewmodel._num.observe(this) {
                if (it.code == 200) {
                    Toast.makeText(this.requireContext(), "登录成功", Toast.LENGTH_SHORT).show()
                    UserManager.saveUserInfo("123", "123")
                } else {
                    Toast.makeText(this.requireContext(), "账号或密码错误", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun getknown(){
        mBinding.btnVisitKnow.setOnClickListener{
            val builder= AlertDialog.Builder(this.requireContext())
            builder.setTitle("登陆须知")
            builder.setMessage("这里是游客登陆，并且登陆后可以保持登录登陆状态")
            builder.setPositiveButton("确定"){ dialog, which ->
                dialog.dismiss()
            }
            val dialog=builder.create()
            dialog.show()
        }
    }

}