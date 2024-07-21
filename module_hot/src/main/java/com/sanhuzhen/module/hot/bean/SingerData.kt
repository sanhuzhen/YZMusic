package com.sanhuzhen.module.hot.bean
import kotlin.collections.List

data class SingerData(
    val list: ListX
)

data class ListX(
    val artists: List<Artist>,
)

data class Artist(
    val id: Long,
    val name: String,
    val picUrl: String,
)

