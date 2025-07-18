package com.example.module.login.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.module.login.databinding.FragmentLoginBinding
//import com.example.module.login.manager.UserManager
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
         //UserManager.initialize(requireContext())
         getAnswer()
         getknown()
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
             val sp: SharedPreferences.Editor = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit()
             mViewmodel._num.observe(viewLifecycleOwner) {
                 if (it.code == 200) {
                     Toast.makeText(this.requireContext(), "登录成功", Toast.LENGTH_SHORT).show()
                     sp.putLong("id", it.profile.userId).apply()
                     Log.d("want","${it.profile.userId}")
                     activity?.finish()
                 } else {
                     Toast.makeText(this.requireContext(), "账号或密码错误", Toast.LENGTH_SHORT).show()
                 }
             }
         }

     }
     fun getknown(){
         mBinding.knowButton.setOnClickListener{
             val builder=AlertDialog.Builder(this.requireContext())
             builder.setTitle("登陆须知")
             builder.setMessage("这里使用邮箱或者手机号登录，并且登陆后可以保持登陆状态")
             builder.setPositiveButton("确定"){ dialog, _ ->
                 dialog.dismiss()
             }
             val dialog=builder.create()
             dialog.show()
         }
     }
 }