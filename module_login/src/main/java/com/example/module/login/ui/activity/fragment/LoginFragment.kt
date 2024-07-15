package com.example.module.login.ui.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.module.login.R
import com.example.module.login.databinding.FragmentLoginBinding
import com.example.module.login.viewmodel.MyViewModel
import com.sanhuzhen.lib.base.BaseFragment


 class LoginFragment : BaseFragment<FragmentLoginBinding> () {
     private val mViewmodel: MyViewModel by lazy { ViewModelProvider(this)[MyViewModel::class.java] }
     var name: String? = null
     var password: String? = null

     override fun getViewBinding(): FragmentLoginBinding {
         return FragmentLoginBinding.inflate(layoutInflater)
     }

     override fun afterCreate() {
         getAnswer()
     }

     private fun getMessage() {
         name = mBinding.registerUsernameEdit.getText().toString()
         password = mBinding.registerPasswordEdit.getText().toString()
         if (name!!.indexOf("@", 0, true) == 1) {
             mViewmodel.getNum(name!!, password!!)
         } else {
             mViewmodel.getNumber(name!!, password!!)
         }

     }

     private fun getAnswer() {
         mBinding.loginButton.setOnClickListener {
             getMessage()
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