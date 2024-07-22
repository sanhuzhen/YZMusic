package com.sanhuzhen.module.musicplayer.bean

data class CommentData(
    val `data`: mData
)

data class mData(
    val comments: List<Comment>,
)

data class Comment(
    val content: String,
    val timeStr: String,
    val user: User,
    val commentId: Long,
    val needDisplayTime: Boolean
)
data class User(
    val avatarUrl: String,
    val userId: Long,
    val nickname: String
)

