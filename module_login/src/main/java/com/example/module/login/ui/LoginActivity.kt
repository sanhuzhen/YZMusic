package com.example.module.login.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    var name:String?=null
    var password:String?=null
    override fun afterCreate() {
        getAnswer()
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }
    private fun getMessage(){
         name= mBinding.registerUsernameEdit.getText().toString()
         password= mBinding.registerPasswordEdit.getText().toString()

    }
    private fun getAnswer(){

        mBinding.loginButton.setOnClickListener {
            getMessage()
            val code=mViewmodel._num.value?.code

            if (name!!.indexOf("@",0,true)==1) {
                mViewmodel.getNum(name!!, password!!)
            }
            else {
                mViewmodel.getNumber(name!!, password!!)
            }


            Log.d("you", "$code")
            name?.let { Log.d("LoginActivity", it) }
            password?.let { Log.d("LoginActivity", it) }

                if (code == 200) {
                    Log.d("you", "$code")
//                startActivity(Intent(this,MainActivity::class.java))
                } else {
                    Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show()
                }

        }
    }


}