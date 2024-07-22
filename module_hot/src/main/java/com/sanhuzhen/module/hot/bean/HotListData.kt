package com.sanhuzhen.module.hot.bean

import kotlin.collections.List

data class HotListData(
    val playlists: List<Playlists>,
)

data class Playlists(
    val coverImgUrl: String,
    val description: String,
    val id: Long,
    val name: String,
)



