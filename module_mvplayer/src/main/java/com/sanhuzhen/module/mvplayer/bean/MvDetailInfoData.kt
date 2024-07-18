package com.sanhuzhen.module.mvplayer.bean

data class MvDetailInfoData(
    val code: Int,
    val commentCount: Int,//评论数
    val liked: Boolean,//是否点赞
    val likedCount: Int,//点赞数
    val shareCount: Int//分享数
)