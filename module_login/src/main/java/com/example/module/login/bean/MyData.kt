package com.example.module.login.bean

data class MyData(
    val code: Int,
    val profile: Profile
)
data class Profile(
    val avatarUrl: String,
    val backgroundUrl: String,
    val userId: Int
)