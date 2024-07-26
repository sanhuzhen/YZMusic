package com.example.module.login.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.module.login.databinding.FragmentLoginByBinding
import com.example.module.login.util.CountDownTimeUtils
import com.example.module.login.viewmodel.MyViewModel
import com.sanhuzhen.lib.base.BaseFragment

class LoginByFragment : BaseFragment<FragmentLoginByBinding>() {
    private val mViewmodel: MyViewModel by lazy { ViewModelProvider(this)[MyViewModel::class.java] }
    var phone: String? = null
    var captcha: String? = null

    override fun getViewBinding(): FragmentLoginByBinding {
        return FragmentLoginByBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {

        getknow()
        setCaptcha()
        getCaptcha()
    }

    private fun getMessage() {
        phone = mBinding.loginUsernameEdit.getText().toString()
        captcha = mBinding.loginPasswordEdit.getText().toString()
    }

    private fun setCaptcha() {
        mBinding.loginGetCode.setOnClickListener {
            phone=mBinding.loginUsernameEdit.text.toString()
            if (phone == null) {
                Toast.makeText(this.requireContext(), "请输入手机号", Toast.LENGTH_SHORT).show()
            } else if (phone!!.length != 11) {
                Toast.makeText(this.requireContext(), "手机号格式错误", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this.requireContext(), "发送验证码", Toast.LENGTH_SHORT).show()
                val mCountDownTimeUtils = CountDownTimeUtils(mBinding.loginGetCode, 60000, 1000)
                mCountDownTimeUtils.start()
                mViewmodel.getSend(phone!!)

            }
        }
    }

    private fun getCaptcha() {
        var youjiachao = 0
        mBinding.loginButton.setOnClickListener {
            youjiachao += 1
            getMessage()
            if (captcha!!.isEmpty()){
                Toast.makeText(this.requireContext(), "请输入验证码", Toast.LENGTH_SHORT).show()
            }else{
                Log.d("you123","$phone-$captcha")
                mViewmodel.getVerify(phone!!, captcha!!)
                var id = 0L
                val sp: SharedPreferences.Editor =
                    requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit()
                mViewmodel._num1.observe(viewLifecycleOwner) {
                    sp.putLong("id", it).apply()
                    id = it
                }
                if (youjiachao == 1)
                {
                    Toast.makeText(this.requireContext(), "服务器开了会小差，请再点一次", Toast.LENGTH_SHORT).show()
                }else{
                    if (id == 0L){
                        Toast.makeText(this.requireContext(), "登录失败", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this.requireContext(), "登录成功", Toast.LENGTH_SHORT).show()
                        Log.d("you", "${it}")
                        activity?.finish()
                    }
                }
            }
        }
            getMessage()
            val sp: SharedPreferences.Editor =
                requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit()


    }

    fun getknow() {
        mBinding.btnKnow.setOnClickListener {
            val builder = AlertDialog.Builder(this.requireContext())
            builder.setTitle("登陆须知")
            builder.setMessage("这里使用手机号验证码登录，登陆可以后实现登陆状态")
            builder.setPositiveButton("确定") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }


}