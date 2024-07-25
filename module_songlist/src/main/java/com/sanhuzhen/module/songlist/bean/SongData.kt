package com.sansHuzsHen.module.songlist.bean

data class SongData(
    val songs: List<Song>
)

data class Song(
    val al: mAl,
    val sAr: List<sAr>,
    val id: Long,
    val mv: Long,
    val name: String,
)

data class mAl(
    val id: Long,
    val name: String,
    val pic: Long,
    val picUrl: String,
    val pic_str: String,
    val tns: List<Any>
)

data class sAr(
    val alias: List<Any>,
    val id: Long,
    val name: String,
    val tns: List<Any>
)

