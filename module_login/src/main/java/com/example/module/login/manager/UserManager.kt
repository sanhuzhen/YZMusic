package com.example.module.login.manager

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.example.module.login.bean.UserInfo


object UserManager {
     private var sharedPreferences: SharedPreferences? = null

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
    }

    /**
     * 保存自动登录的用户信息
     */
    fun saveUserInfo(username: String, password: String) {
        val editor = sharedPreferences?.edit()
        editor?.putString("USER_NAME", username)
        editor?.putString("PASSWORD", password)
        editor?.apply()
    }

    /**
     * 获取用户信息
     */
    fun getUserInfo(): UserInfo? {
        val userName = sharedPreferences?.getString("USER_NAME", "")
        val password = sharedPreferences?.getString("PASSWORD", "")
        return if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            UserInfo(userName!!, password!!)
        } else {
            null
        }
    }

    /**
     * 检查userInfo中是否有数据
     */
    fun hasUserInfo(): Boolean {
        val userInfo = getUserInfo()
        return userInfo != null && !TextUtils.isEmpty(userInfo.userName) && !TextUtils.isEmpty(
            userInfo.password
        )
    }
}