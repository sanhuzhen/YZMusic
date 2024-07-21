package com.sanhuzhen.module.hot.bean

data class TopListData(
    val list: List<ListY>
)


data class ListY(
    val coverImgUrl: String,
    val description: String,
    val id: Long,
    val name: String,
)