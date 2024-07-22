package com.sanhuzhen.module.mine.bean

data class FavouriteData(
    val playlist: PlaylistX,
)

data class PlaylistX(
    val coverImgUrl: String,
    val creator: Creator,
    val id: Long,
    val name: String,
    val tracks: List<Track>,
)

data class Creator(
    val avatarUrl: String,
    val nickname: String,
    val userId: Long
)
data class Track(
    val al: AlX,
    val ar: List<ArX>,
    val id: Long,
    val name: String,
)
data class AlX(
    val picUrl: String,
)
data class ArX(
    val id: Long,
    val name: String,
)