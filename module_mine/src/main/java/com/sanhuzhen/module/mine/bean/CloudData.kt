package com.sanhuzhen.module.mine.bean

data class CloudData(
    val `data`: List<Data>,
)

data class Data(
    val simpleSong: SimpleSong,
)

data class SimpleSong(
    val al: AlY,
    val id: Long,
    val name: String,
)

data class AlY(
    val picUrl: String,
)
