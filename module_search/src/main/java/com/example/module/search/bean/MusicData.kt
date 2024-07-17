package com.example.module.search.bean

data class MusicData(
    val result: ResultMusic
)

data class ResultMusic(
    val songs: List<Song>
)

data class Song(
    val album: Album,
    val artists: List<ArtistX>,
    val id: Long,
    val mvid: Long,
    val name: String,
)

data class Album(
    val id: Long,
    val name: String,
)

data class ArtistX(
    val id: Long,
    val name: String,
    val picUrl: Any,
)