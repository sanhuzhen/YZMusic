package com.sanhuzhen.yzmusic.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.yzmusic.databinding.ActivitySplashBinding

/**
 * @author: sanhuzhen
 * @date: 2024/7/15
 * @description:开屏页
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private lateinit var countDownTimer: CountDownTimer
    override fun afterCreate() {
        countDownTimer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }.start()
    }

    override fun getViewBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }
}