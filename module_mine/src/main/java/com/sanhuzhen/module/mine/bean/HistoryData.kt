package com.sanhuzhen.module.mine.bean

data class HistoryData(
    val weekData: List<WeekData>
)

data class WeekData(
    val song: Song
)

data class Song(
    val al: Al,
    val ar: List<Ar>,
    val id: Long,
    val name: String,
)

data class Al(
    val name: String,
    val picUrl: String,
)

data class Ar(
    val id: Long,
    val name: String,
)
