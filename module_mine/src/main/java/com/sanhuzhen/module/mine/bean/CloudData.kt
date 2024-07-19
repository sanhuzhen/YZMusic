package com.sanhuzhen.module.mine.bean

data class CloudData(
    val code: Int,
    val count: Int,
    val `data`: List<Data>,
    val hasMore: Boolean,
    val maxSize: String,
    val size: String,
    val upgradeSign: Int
)

data class Data(
    val addTime: Long,
    val album: String,
    val artist: String,
    val bitrate: Int,
    val cover: Int,
    val coverId: String,
    val fileName: String,
    val fileSize: Int,
    val lyricId: String,
    val simpleSong: SimpleSong,
    val songId: Long,
    val songName: String,
    val version: Int
)

data class SimpleSong(
    val a: Any,
    val al: AlY,
    val alia: List<String>,
    val ar: List<ArY>,
    val cd: String,
    val cf: String,
    val copyright: Int,
    val cp: Int,
    val crbt: Any,
    val djId: Int,
    val dt: Int,
    val fee: Int,
    val ftype: Int,
    val h: H,
    val id: Long,
    val l: L,
    val m: M,
    val mark: Long,
    val mst: Int,
    val mv: Int,
    val name: String,
    val no: Int,
    val noCopyrightRcmd: Any,
    val originCoverType: Int,
    val originSongSimpleData: OriginSongSimpleData,
    val pop: Int,
    val privilege: Privilege,
    val pst: Int,
    val publishTime: Long,
    val rt: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Int,
    val rurl: Any,
    val s_id: Long,
    val single: Int,
    val st: Int,
    val t: Int,
    val tns: List<String>,
    val v: Int,
    val videoInfo: VideoInfo
)

data class AlY(
    val id: Int,
    val name: String,
    val pic: Long,
    val picUrl: String,
    val pic_str: String,
    val tns: List<Any>
)

data class ArY(
    val alias: List<Any>,
    val id: Int,
    val name: String,
    val tns: List<Any>
)

data class H(
    val br: Int,
    val fid: Int,
    val size: Int,
    val vd: Int
)

data class L(
    val br: Int,
    val fid: Int,
    val size: Int,
    val vd: Int
)

data class M(
    val br: Int,
    val fid: Int,
    val size: Int,
    val vd: Int
)

data class OriginSongSimpleData(
    val albumMeta: AlbumMeta,
    val artists: List<Artist>,
    val name: String,
    val songId: Int
)

data class Privilege(
    val chargeInfoList: List<ChargeInfo>,
    val cp: Int,
    val cs: Boolean,
    val dl: Int,
    val dlLevel: String,
    val downloadMaxBrLevel: String,
    val downloadMaxbr: Int,
    val fee: Int,
    val fl: Int,
    val flLevel: String,
    val flag: Int,
    val freeTrialPrivilege: FreeTrialPrivilege,
    val id: Long,
    val maxBrLevel: String,
    val maxbr: Int,
    val payed: Int,
    val pl: Int,
    val plLevel: String,
    val playMaxBrLevel: String,
    val playMaxbr: Int,
    val preSell: Boolean,
    val rscl: Any,
    val sp: Int,
    val st: Int,
    val subp: Int,
    val toast: Boolean
)

data class VideoInfo(
    val moreThanOne: Boolean,
    val video: Video
)

data class AlbumMeta(
    val id: Int,
    val name: String
)

data class Artist(
    val id: Int,
    val name: String
)

data class ChargeInfo(
    val chargeMessage: Any,
    val chargeType: Int,
    val chargeUrl: Any,
    val rate: Int
)

data class FreeTrialPrivilege(
    val listenType: Any,
    val resConsumable: Boolean,
    val userConsumable: Boolean
)

data class Video(
    val artists: Any,
    val coverUrl: String,
    val playTime: Int,
    val publishTime: Long,
    val title: String,
    val type: Int,
    val vid: String
)