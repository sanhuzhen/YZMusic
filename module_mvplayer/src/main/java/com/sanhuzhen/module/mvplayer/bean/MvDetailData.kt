package com.sanhuzhen.module.mvplayer.bean

data class MvDetailData(
    val code: Int,
    val `data`: Data
)

data class Data(
    val code: Int,
    val expi: Int,
    val fee: Int,
    val id: Int,
    val md5: String,
    val msg: String,
    val mvFee: Int,
    val promotionVo: Any,
    val r: Int,
    val size: Int,
    val st: Int,
    val url: String
)