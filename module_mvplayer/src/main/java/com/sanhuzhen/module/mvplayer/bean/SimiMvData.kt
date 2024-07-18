package com.sanhuzhen.module.mvplayer.bean

data class SimiMvData(
    val code: Int,
    val mvs: List<Mv>
)

data class Mv(
    val alg: String,
    val artistId: Int,
    val artistName: String,
    val artists: List<sArtist>,
    val briefDesc: Any,
    val cover: String,
    val desc: Any,
    val duration: Int,
    val id: Int,
    val mark: Int,
    val name: String,
    val playCount: Int
)

data class sArtist(
    val alias: List<String>,
    val id: Int,
    val name: String,
    val transNames: Any
)