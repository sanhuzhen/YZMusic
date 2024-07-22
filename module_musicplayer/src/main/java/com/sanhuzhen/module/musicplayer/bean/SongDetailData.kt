package com.sanhuzhen.module.musicplayer.bean

data class SongDetailData(
    val songs: List<Song>
)


data class Song(
    val al: Al,
    val ar: List<Ar>,
    val mv: Long,
    val name: String
)

data class Al(
    val id: Long,
    val name: String,
    val pic: Long,
    val picUrl: String,
    val pic_str: String

)

data class Ar(
    val id: Long,
    val name: String
)
