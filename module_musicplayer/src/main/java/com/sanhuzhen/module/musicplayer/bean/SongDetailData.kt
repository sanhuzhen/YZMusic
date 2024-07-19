package com.sanhuzhen.module.musicplayer.bean

data class SongDetailData(
    val code: Long,
    val privileges: List<Privilege>,
    val songs: List<Song>
)

data class Privilege(
    val chargeInfoList: List<ChargeInfo>,
    val code: Long,
    val cp: Long,
    val cs: Boolean,
    val dl: Long,
    val dlLevel: String,
    val downloadMaxBrLevel: String,
    val downloadMaxbr: Long,
    val fee: Long,
    val fl: Long,
    val flLevel: String,
    val flag: Long,
    val freeTrialPrivilege: FreeTrialPrivilege1,
    val id: Long,
    val maxBrLevel: String,
    val maxbr: Long,
    val message: Any,
    val payed: Long,
    val pl: Long,
    val plLevel: String,
    val playMaxBrLevel: String,
    val playMaxbr: Long,
    val preSell: Boolean,
    val rightSource: Long,
    val rscl: Long,
    val sp: Long,
    val st: Long,
    val subp: Long,
    val toast: Boolean
)

data class Song(
    val a: Any,
    val al: Al,
    val alia: List<Any>,
    val ar: List<Ar>,
    val awardTags: Any,
    val cd: String,
    val cf: String,
    val copyright: Long,
    val cp: Long,
    val crbt: Any,
    val djId: Long,
    val dt: Long,
    val entertainmentTags: Any,
    val fee: Long,
    val ftype: Long,
    val h: H,
    val hr: Any,
    val id: Long,
    val l: L,
    val m: M,
    val mark: Long,
    val mst: Long,
    val mv: Long,
    val name: String,
    val no: Long,
    val noCopyrightRcmd: Any,
    val originCoverType: Long,
    val originSongSimpleData: Any,
    val pop: Long,
    val pst: Long,
    val publishTime: Long,
    val resourceState: Boolean,
    val rt: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Long,
    val rurl: Any,
    val s_id: Long,
    val single: Long,
    val songJumpInfo: Any,
    val sq: Sq,
    val st: Long,
    val t: Long,
    val tagPicList: Any,
    val tns: List<String>,
    val v: Long,
    val version: Long
)

data class ChargeInfo(
    val chargeMessage: Any,
    val chargeType: Long,
    val chargeUrl: Any,
    val rate: Long
)

data class FreeTrialPrivilege1(
    val cannotListenReason: Long,
    val listenType: Any,
    val playReason: Any,
    val resConsumable: Boolean,
    val userConsumable: Boolean
)

data class Al(
    val id: Long,
    val name: String,
    val pic: Long,
    val picUrl: String,
    val pic_str: String,
    val tns: List<Any>
)

data class Ar(
    val alias: List<Any>,
    val id: Long,
    val name: String,
    val tns: List<Any>
)

data class H(
    val br: Long,
    val fid: Long,
    val size: Long,
    val sr: Long,
    val vd: Long
)

data class L(
    val br: Long,
    val fid: Long,
    val size: Long,
    val sr: Long,
    val vd: Long
)

data class M(
    val br: Long,
    val fid: Long,
    val size: Long,
    val sr: Long,
    val vd: Long
)

data class Sq(
    val br: Long,
    val fid: Long,
    val size: Long,
    val sr: Long,
    val vd: Long
)