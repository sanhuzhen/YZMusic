package com.example.module.login.ui.activity.fragment

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.module.login.databinding.FragmentLoginByBinding
import com.example.module.login.util.CountDownTimeUtils
import com.example.module.login.viewmodel.MyViewModel
import com.sanhuzhen.lib.base.BaseFragment

class LoginByFragment : BaseFragment<FragmentLoginByBinding>() {
    private val mViewmodel: MyViewModel by lazy { ViewModelProvider(this)[MyViewModel::class.java] }
    var phone:String?=null
    var captcha:String?=null
    override fun getViewBinding(): FragmentLoginByBinding {
        return FragmentLoginByBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        setCaptcha()
        getCaptcha()
    }
    private fun getMessage(){
        phone=mBinding.loginUsernameEdit.getText().toString()
        captcha=mBinding.loginPasswordEdit.getText().toString()
    }
    private fun setCaptcha(){
        mBinding.loginGetCode.setOnClickListener {
            val mCountDownTimeUtils= CountDownTimeUtils(mBinding.loginGetCode,60000,1000)
            mCountDownTimeUtils.start()
            getMessage()
            mViewmodel.getSend(phone!!)
        }
    }
    private fun getCaptcha(){
        mBinding.loginButton.setOnClickListener {
            getMessage()
            mViewmodel.getVerify(phone!!,captcha!!)
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