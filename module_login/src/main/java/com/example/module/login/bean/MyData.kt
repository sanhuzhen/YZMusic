package com.example.module.login.bean

data class MyData(
    val code: Int,
    val profile: Profile,
    val loginType: Int,
)
data class Profile(
    val avatarUrl: String,
    val backgroundUrl: String,
    val userId: Long,
    val nickname: String,
)