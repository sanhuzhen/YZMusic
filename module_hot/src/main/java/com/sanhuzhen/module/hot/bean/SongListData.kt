package com.sanhuzhen.module.hot.bean

data class SongListData(
    val playlist: Playlist,
)

data class Playlist(
    val backgroundCoverUrl: Any,
    val coverImgUrl: String,
    val id: Long,
    val name: String,
    val tracks: List<Track>,
)

data class Track(
    val al: Al,
    val ar: List<Ar>,
    val id: Long,
    val name: String,
)

data class Al(
    val id: Int,
    val name: String,
    val pic: Long,
    val picUrl: String,
    val pic_str: String,
    val tns: List<String>
)

data class Ar(
    val alias: List<Any>,
    val id: Long,
    val name: String,
    val tns: List<Any>
)