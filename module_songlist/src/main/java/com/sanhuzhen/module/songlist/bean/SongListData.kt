package com.sanhuzhen.module.songlist.bean

data class SongListData(
    val code: Long,
    val fromUserCount: Long,
    val fromUsers: Any,
    val playlist: Playlist,
    val privileges: List<Privilege>,
    val relatedVideos: RelatedVideos,
    val resEntrance: Any,
    val sharedPrivilege: Any,
    val songFromUsers: Any,
    val urls: Any
)

data class Playlist(
    val adType: Long,
    val algTags: Any,
    val backgroundCoverId: Long,
    val backgroundCoverUrl: Any,
    val bannedTrackIds: Any,
    val cloudTrackCount: Long,
    val commentCount: Long,
    val commentThreadId: String,
    val copied: Boolean,
    val coverImgId: Long,
    val coverImgId_str: String,
    val coverImgUrl: String,
    val coverStatus: Long,
    val createTime: Long,
    val creator: Creator,
    val description: String,
    val detailPageTitle: Any,
    val displayTags: Any,
    val distributeTags: List<Any>,
    val englishTitle: Any,
    val gradeStatus: String,
    val highQuality: Boolean,
    val historySharedUsers: Any,
    val id: Long,
    val mvResourceInfos: Any,
    val name: String,
    val newDetailPageRemixVideo: Any,
    val newImported: Boolean,
    val officialPlaylistType: Any,
    val opRecommend: Boolean,
    val ordered: Boolean,
    val playCount: Long,
    val playlistType: String,
    val privacy: Long,
    val relateResType: Any,
    val remixVideo: Any,
    val score: Any,
    val shareCount: Long,
    val sharedUsers: Any,
    val specialType: Long,
    val status: Long,
    val subscribed: Boolean,
    val subscribedCount: Long,
    val subscribers: List<Subscriber>,
    val tags: List<String>,
    val titleImage: Long,
    val titleImageUrl: Any,
    val trackCount: Long,
    val trackIds: List<TrackId>,
    val trackNumberUpdateTime: Long,
    val trackUpdateTime: Long,
    val tracks: List<Track>,
    val trialMode: Long,
    val updateFrequency: Any,
    val updateTime: Long,
    val userId: Long,
    val videoIds: Any,
    val videos: Any
)

data class Privilege(
    val chargeInfoList: List<ChargeInfo>,
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
    val freeTrialPrivilege: FreeTrialPrivilege,
    val id: Long,
    val maxBrLevel: String,
    val maxbr: Long,
    val paidBigBang: Boolean,
    val payed: Long,
    val pc: Any,
    val pl: Long,
    val plLevel: String,
    val playMaxBrLevel: String,
    val playMaxbr: Long,
    val preSell: Boolean,
    val realPayed: Long,
    val rightSource: Long,
    val rscl: Any,
    val sp: Long,
    val st: Long,
    val subp: Long,
    val toast: Boolean
)

class RelatedVideos

data class Creator(
    val accountStatus: Long,
    val anchor: Boolean,
    val authStatus: Long,
    val authenticationTypes: Long,
    val authority: Long,
    val avatarDetail: Any,
    val avatarImgId: Long,
    val avatarImgIdStr: String,
    val avatarImgId_str: String,
    val avatarUrl: String,
    val backgroundImgId: Long,
    val backgroundImgIdStr: String,
    val backgroundUrl: String,
    val birthday: Long,
    val city: Long,
    val defaultAvatar: Boolean,
    val description: String,
    val detailDescription: String,
    val djStatus: Long,
    val expertTags: Any,
    val experts: Any,
    val followed: Boolean,
    val gender: Long,
    val mutual: Boolean,
    val nickname: String,
    val province: Long,
    val remarkName: Any,
    val signature: String,
    val userId: Long,
    val userType: Long,
    val vipType: Long
)

data class Subscriber(
    val accountStatus: Long,
    val anchor: Boolean,
    val authStatus: Long,
    val authenticationTypes: Long,
    val authority: Long,
    val avatarDetail: Any,
    val avatarImgId: Long,
    val avatarImgIdStr: String,
    val avatarImgId_str: String,
    val avatarUrl: String,
    val backgroundImgId: Long,
    val backgroundImgIdStr: String,
    val backgroundUrl: String,
    val birthday: Long,
    val city: Long,
    val defaultAvatar: Boolean,
    val description: String,
    val detailDescription: String,
    val djStatus: Long,
    val expertTags: Any,
    val experts: Any,
    val followed: Boolean,
    val gender: Long,
    val mutual: Boolean,
    val nickname: String,
    val province: Long,
    val remarkName: Any,
    val signature: String,
    val userId: Long,
    val userType: Long,
    val vipType: Long
)

data class TrackId(
    val alg: Any,
    val at: Long,
    val dpr: Any,
    val f: Any,
    val id: Long,
    val rcmdReason: String,
    val sc: Any,
    val sr: Any,
    val t: Long,
    val uid: Long,
    val v: Long
)

data class Track(
    val a: Any,
    val al: Al,
    val alg: Any,
    val alia: List<String>,
    val ar: List<Ar>,
    val awardTags: Any,
    val cd: String,
    val cf: String,
    val copyright: Long,
    val cp: Long,
    val crbt: Any,
    val displayReason: Any,
    val djId: Long,
    val dt: Long,
    val entertainmentTags: Any,
    val fee: Long,
    val ftype: Long,
    val h: H,
    val hr: Hr,
    val id: Long,
    val l: L,
    val m: M,
    val mark: Long,
    val mst: Long,
    val mv: Long,
    val name: String,
    val no: Long,
    val noCopyrightRcmd: NoCopyrightRcmd,
    val originCoverType: Long,
    val originSongSimpleData: OriginSongSimpleData,
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
    val version: Long,
    val videoInfo: VideoInfo
)

data class Al(
    val id: Long,
    val name: String,
    val pic: Long,
    val picUrl: String,
    val pic_str: String,
    val tns: List<String>
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

data class Hr(
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

data class NoCopyrightRcmd(
    val expInfo: Any,
    val songId: Any,
    val thirdPartySong: Any,
    val type: Long,
    val typeDesc: String
)

data class OriginSongSimpleData(
    val albumMeta: AlbumMeta,
    val artists: List<Artist>,
    val name: String,
    val songId: Long
)

data class Sq(
    val br: Long,
    val fid: Long,
    val size: Long,
    val sr: Long,
    val vd: Long
)

data class VideoInfo(
    val moreThanOne: Boolean,
    val video: Video
)

data class AlbumMeta(
    val id: Long,
    val name: String
)

data class Artist(
    val id: Long,
    val name: String
)

data class Video(
    val alias: Any,
    val artists: Any,
    val coverUrl: String,
    val playTime: Long,
    val publishTime: Long,
    val title: String,
    val type: Long,
    val vid: String
)

data class ChargeInfo(
    val chargeMessage: Any,
    val chargeType: Long,
    val chargeUrl: Any,
    val rate: Long
)

data class FreeTrialPrivilege(
    val cannotListenReason: Long,
    val listenType: Long,
    val playReason: Any,
    val resConsumable: Boolean,
    val userConsumable: Boolean
)