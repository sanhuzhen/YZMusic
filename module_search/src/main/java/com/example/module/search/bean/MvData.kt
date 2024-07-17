package com.example.module.search.bean

data class MvData(
    val result: ResultMv
)

data class ResultMv(
    val mvs: List<Mv>
)

data class Mv(
    val artists: List<ArtistMv>,
    val cover: String,
    val id: Long,
    val name: String,

)

data class ArtistMv(
    val id: Long,
    val name: String,
)