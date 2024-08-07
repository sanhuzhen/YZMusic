package com.example.module.search.bean

data class ArtistsData(
    val result: ResultArtists
)

data class ResultArtists(
    val artists: List<Artist>,
)

data class Artist(
    val id: Long,
    val name: String,
    val picUrl: String,
)