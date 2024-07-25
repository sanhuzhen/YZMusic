package com.sanhuzhen.module.songlist.bean

data class MvData(
    val mvs: List<Mv>
)

data class Mv(
    val artist: mvArtist,
    val artistName: String,
    val duration: Long,
    val id: Long,
    val imgurl: String,
    val imgurl16v9: String,
    val name: String,
    val playCount: Long,
    val publishTime: String,
    val status: Long,
    val subed: Boolean
)

data class mvArtist(
    val albumSize: Long,
    val alias: List<Any>,
    val briefDesc: String,
    val id: Long,
    val img1v1Id: Long,
    val img1v1Id_str: String,
    val img1v1Url: String,
    val musicSize: Long,
    val name: String,
    val picId: Long,
    val picUrl: String,
    val topicPerson: Long,
    val trans: String
)