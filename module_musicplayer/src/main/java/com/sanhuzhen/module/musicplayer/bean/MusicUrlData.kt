package com.sanhuzhen.module.musicplayer.bean

data class MusicUrlData(
    val code: Long,
    val `data`: List<Data>
)

data class Data(
    val br: Long,
    val canExtend: Boolean,
    val channelLayout: Any,
    val code: Long,
    val effectTypes: Any,
    val encodeType: String,
    val expi: Long,
    val fee: Long,
    val flag: Long,
    val freeTimeTrialPrivilege: FreeTimeTrialPrivilege,
    val freeTrialInfo: Any,
    val freeTrialPrivilege: FreeTrialPrivilege,
    val gain: Double,
    val id: Long,
    val level: String,
    val levelConfuse: Any,
    val md5: String,
    val message: Any,
    val payed: Long,
    val peak: Long,
    val podcastCtrp: Any,
    val rightSource: Long,
    val size: Long,
    val time: Long,
    val type: String,
    val uf: Any,
    val url: String,
    val urlSource: Long
)

data class FreeTimeTrialPrivilege(
    val remaLongime: Long,
    val resConsumable: Boolean,
    val type: Long,
    val userConsumable: Boolean
)

data class FreeTrialPrivilege(
    val cannotListenReason: Any,
    val listenType: Any,
    val playReason: Any,
    val resConsumable: Boolean,
    val userConsumable: Boolean
)