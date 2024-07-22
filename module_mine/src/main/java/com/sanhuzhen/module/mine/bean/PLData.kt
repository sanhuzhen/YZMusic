package com.sanhuzhen.module.mine.bean

data class PLData(
    val playlist: List<Playlist>,

)

data class Playlist(
    val id: Long,
    val name: String,
)

