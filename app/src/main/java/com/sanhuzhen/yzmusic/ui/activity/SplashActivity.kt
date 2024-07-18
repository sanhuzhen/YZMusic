package com.sanhuzhen.yzmusic.ui.activity

import com.sanhuzhen.yzmusic.ui.activity.MainActivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.sanhuzhen.yzmusic.R
import com.sanhuzhen.yzmusic.databinding.ActivitySplashBinding

/**
 * @author: sanhuzhen
 * @date: 2024/7/15
 * @description:开屏页
 */

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

//    val sp=getSharedPreferences("userInfo", MODE_PRIVATE)
//    val name=sp.getString("USER_NAME", "123")
//    val password=sp.getString("PASSWORD", "123")
//    private val mViewmodel: MyViewModel by lazy { ViewModelProvider(this)[MyViewModel::class.java] }
//    val GO_HOME = 1
//    val GO_LOGIN = 2
//    private val mHandler = @SuppressLint("HandlerLeak")
//    object : Handler() {
//        override fun handleMessage(msg: Message) {
//            when (msg.what) {
//                GO_HOME -> {
//                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//                GO_LOGIN -> {
//                    val intent2 = Intent(this@SplashActivity, LoginActivity::class.java)
//                    startActivity(intent2)
//                    finish()
//                }
//            }
//        }
//    }


    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        UserManager.initialize(this)
        countDownTimer = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
//                if (UserManager.hasUserInfo()) {
//                    // 自动登录判断，SharePreferences中有数据，则跳转到主页
//                    mHandler.sendEmptyMessageDelayed(GO_HOME, 1000)
//                    if (name?.indexOf("@", 0, true)  ==1){
//                        mViewmodel.getNum(name!!, password!!)
//                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//                    }
//                    else if(name?.length ==11){
//                        mViewmodel.getNumber(name!!, password!!)
//                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//                    }
//                    else{
//                        mViewmodel.getVisit()
//                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//                    }
//
//                } else {
//                    // 没数据则跳转到登录页
//                    mHandler.sendEmptyMessageDelayed(GO_LOGIN, 1000)
//                }
//            }
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

        }.start()

    }



}


