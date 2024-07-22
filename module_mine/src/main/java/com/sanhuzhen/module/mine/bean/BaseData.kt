package com.sanhuzhen.module.mine.bean

data class BaseData(
    val profile: Profile,
)



data class Profile(
    val avatarUrl: String,
    val nickname: String,
    val userId: Long,
)

