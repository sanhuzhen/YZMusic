package com.example.module.login.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.module.login.R
import com.example.module.login.databinding.ActivityLoginBinding
import com.example.module.login.viewmodel.MyViewModel
import com.sanhuzhen.lib.base.BaseActivity

class LoginActivity : BaseActivity <ActivityLoginBinding>(){
    private val mViewmodel: MyViewModel by lazy { ViewModelProvider(this)[MyViewModel::class.java] }
    private var code: Int? = null
    var name:String?=null
    var password:String?=null
    override fun afterCreate() {
        getMessage()
        mViewmodel._num.observe(this){
            code = it.code
        }
        mViewmodel.getNum(name!!, password!!)
        mViewmodel.getNumber(name!!, password!!)
        getAnswer()
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }
    fun getMessage(){
         name= mBinding.registerUsernameEdit.toString()
         password= mBinding.registerPasswordEdit.toString()
    }
    fun getAnswer(){
        mBinding.loginButton.setOnClickListener {
            if (code==200){
//                startActivity(Intent(this,MainActivity::class.java))
            }else{
                Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show()
            }
        }
    }


}