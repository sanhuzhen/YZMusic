package com.sanhuzhen.module.songlist.bean

data class SimiSingerData(
    val artists: List<sArtist>,
    val code: Long
)

data class sArtist(
    val accountId: Long,
    val albumSize: Long,
    val alg: String,
    val alias: List<String>,
    val briefDesc: String,
    val fansCount: Long,
    val followed: Boolean,
    val id: Long,
    val identifyTag: Any,
    val img1v1Id: Long,
    val img1v1Id_str: String,
    val img1v1Url: String,
    val isSubed: Any,
    val musicSize: Long,
    val mvSize: Any,
    val name: String,
    val picId: Long,
    val picId_str: String,
    val picUrl: String,
    val publishTime: Any,
    val showPrivateMsg: Any,
    val topicPerson: Long,
    val trans: String,
    val transNames: Any
)