package com.sanhuzhen.module.recommend.bean


data class HomeData(
    val code: Int,
    val `data`: Data
)

data class Data(
    val blocks: List<Block>,
)



data class Block(
    val action: String,
    val actionType: String,
    val alg: String,
    val blockCode: String,
    val blockDemote: Boolean,
    val blockStyle: Int,
    val canClose: Boolean,
    val canFeedback: Boolean,
    val creatives: List<Creative>,
    val crossPlatformConfig: CrossPlatformConfig,
    val dislikeShowType: Int,
    val extInfo: ExtInfo,
    val logInfo: String,
    val resourceIdList: List<String>,
    val showType: String,
    val sort: Int,
    val uiElement: UiElementXX
)





data class Creative(
    val action: String,
    val actionType: String,
    val alg: String,
    val creativeId: String,
    val creativeType: String,
    val logInfo: String,
    val position: Int,
    val resources: List<Resource>,
    val uiElement: UiElementX
)

data class CrossPlatformConfig(
    val containerType: String,
    val rnContent: RnContent
)

data class ExtInfo(
    val banners: List<Banner>
)

data class UiElementXX(
    val button: Button,
    val subTitle: SubTitleX,
    val rcmdShowType: String,
)

data class Resource(
    val action: String,
    val actionType: String,
    val alg: String,
    val ctrp: Any,
    val likedCount: Any,
    val logInfo: String,
    val playParams: Any,
    val position: Any,
    val replyCount: Any,
    val resourceContentList: Any,
    val resourceExtInfo: ResourceExtInfo,
    val resourceId: String,
    val resourceState: Any,
    val resourceType: String,
    val resourceUrl: Any,
    val uiElement: UiElement,
    val valid: Boolean
)

data class UiElementX(
    val image: ImageX,
    val labelTexts: List<String>,

    val rcmdShowType: String,
    val subTitle: SubTitleX
)

data class ResourceExtInfo(
    val artists: List<Artist>,
    val commentSimpleData: CommentSimpleData,
    val hasListened: Boolean,
    val highQuality: Boolean,
    val playCount: Int,
    val song: Song,
    val songData: SongData,
    val songPrivilege: SongPrivilege,
    val specialType: Int
)

data class UiElement(
    val image: Image,
    val labelTexts: List<String>,
    val mainTitle: MainTitle,
    val rcmdShowType: String,
    val subTitle: SubTitle
)

data class Artist(
    val albumSize: Int,
    val alias: List<Any>,
    val briefDesc: String,
    val id: Int,
    val img1v1Id: Int,
    val img1v1Url: String,
    val musicSize: Int,
    val name: String,
    val picId: Int,
    val picUrl: String,
    val topicPerson: Int,
    val trans: String
)

data class CommentSimpleData(
    val commentId: Int,
    val content: String,
    val threadId: String,
    val userId: Int,
    val userName: String
)

data class Song(
    val a: Any,
    val al: Al,
    val alia: List<String>,
    val ar: List<Ar>,
    val cd: String,
    val cf: String,
    val copyright: Int,
    val cp: Int,
    val crbt: Any,
    val djId: Int,
    val dt: Int,
    val entertainmentTags: Any,
    val fee: Int,
    val ftype: Int,
    val h: H,
    val hr: Hr,
    val id: Int,
    val l: L,
    val m: M,
    val mark: Long,
    val mst: Int,
    val mv: Int,
    val name: String,
    val no: Int,
    val noCopyrightRcmd: Any,
    val originCoverType: Int,
    val originSongSimpleData: Any,
    val pop: Int,
    val pst: Int,
    val publishTime: Long,
    val resourceState: Boolean,
    val rt: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Int,
    val rurl: Any,
    val s_id: Int,
    val single: Int,
    val songJumpInfo: Any,
    val sq: Sq,
    val st: Int,
    val t: Int,
    val tagPicList: Any,
    val tns: List<String>,
    val v: Int,
    val version: Int,
    val videoInfo: VideoInfo
)

data class SongData(
    val album: Album,
    val alias: List<String>,
    val artists: List<Artist>,
    val audition: Any,
    val bMusic: BMusic,
    val commentThreadId: String,
    val copyFrom: String,
    val copyright: Int,
    val copyrightId: Int,
    val crbt: Any,
    val dayPlays: Int,
    val disc: String,
    val duration: Int,
    val fee: Int,
    val ftype: Int,
    val hMusic: HMusic,
    val hearTime: Int,
    val hrMusic: HrMusic,
    val id: Int,
    val lMusic: LMusic,
    val mMusic: MMusic,
    val mark: Int,
    val mp3Url: Any,
    val mvid: Int,
    val name: String,
    val no: Int,
    val noCopyrightRcmd: Any,
    val originCoverType: Int,
    val originSongSimpleData: Any,
    val playedNum: Int,
    val popularity: Int,
    val position: Int,
    val ringtone: String,
    val rtUrl: Any,
    val rtUrls: List<Any>,
    val rtype: Int,
    val rurl: Any,
    val score: Int,
    val sign: Any,
    val single: Int,
    val sqMusic: SqMusic,
    val starred: Boolean,
    val starredNum: Int,
    val status: Int,
    val transName: String,
    val transNames: List<String>
)

data class SongPrivilege(
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
    val id: Int,
    val maxBrLevel: String,
    val maxbr: Int,
    val paidBigBang: Boolean,
    val payed: Int,
    val pc: Any,
    val pl: Int,
    val plLevel: String,
    val playMaxBrLevel: String,
    val playMaxbr: Int,
    val preSell: Boolean,
    val realPayed: Int,
    val rightSource: Int,
    val rscl: Any,
    val sp: Int,
    val st: Int,
    val subp: Int,
    val toast: Boolean
)

data class Al(
    val id: Int,
    val name: String,
    val pic: Long,
    val picUrl: String,
    val pic_str: String,
    val tns: List<Any>
)

data class Ar(
    val alias: List<Any>,
    val id: Int,
    val name: String,
    val tns: List<Any>
)

data class H(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Int
)

data class Hr(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Int
)

data class L(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Int
)

data class M(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Int
)

data class Sq(
    val br: Int,
    val fid: Int,
    val size: Int,
    val sr: Int,
    val vd: Int
)

data class VideoInfo(
    val moreThanOne: Boolean,
    val video: Video
)

data class Video(
    val alias: Any,
    val artists: Any,
    val coverUrl: String,
    val playTime: Int,
    val publishTime: Long,
    val title: String,
    val type: Int,
    val vid: String
)

data class Album(
    val alias: List<String>,
    val artist: Artist,
    val artists: List<Artist>,
    val blurPicUrl: String,
    val briefDesc: String,
    val commentThreadId: String,
    val company: String,
    val companyId: Int,
    val copyrightId: Int,
    val description: String,
    val gapless: Int,
    val id: Int,
    val mark: Int,
    val name: String,
    val onSale: Boolean,
    val pic: Long,
    val picId: Long,
    val picId_str: String,
    val picUrl: String,
    val publishTime: Long,
    val size: Int,
    val songs: List<Any>,
    val status: Int,
    val subType: String,
    val tags: String,
    val transName: Any,
    val type: String
)

data class BMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Int
)

data class HMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Int
)

data class HrMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Int
)

data class LMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Int
)

data class MMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Int
)

data class SqMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: Long,
    val name: Any,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Int
)

data class ChargeInfo(
    val chargeMessage: Any,
    val chargeType: Int,
    val chargeUrl: Any,
    val rate: Int
)

data class FreeTrialPrivilege(
    val cannotListenReason: Int,
    val listenType: Int,
    val playReason: Any,
    val resConsumable: Boolean,
    val userConsumable: Boolean
)

data class Image(
    val action: String,
    val imageUrl: String,
    val imageUrl2: String,
    val purePicture: Boolean,
    val title: String
)

data class MainTitle(
    val canShowTitleLogo: Boolean,
    val title: String
)

data class SubTitle(
    val canShowTitleLogo: Boolean,
    val title: String,
    val titleType: String
)

data class ImageX(
    val imageUrl: String,
    val purePicture: Boolean
)

data class SubTitleX(
    val canShowTitleLogo: Boolean,
    val title: String
)

data class RnContent(
    val component: String,
    val engineId: String,
    val estimatedHeight: Int,
    val estimatedRatio: String,
    val moduleName: String,
    val params: Params
)

class Params

data class Banner(
    val adDispatchJson: String,
    val adLocation: String,
    val adSource: String,
    val adid: String,
    val adurlV2: String,
    val alg: String,
    val bannerBizType: String,
    val bannerId: String,
    val dynamicVideoData: Any,
    val encodeId: String,
    val event: Any,
    val exclusive: Boolean,
    val extMonitor: List<ExtMonitor>,
    val extMonitorInfo: ExtMonitorInfo,
    val logContext: Any,
    val mainTitle: Any,
    val monitorBlackList: Any,
    val monitorClick: String,
    val monitorClickList: List<Any>,
    val monitorImpress: String,
    val monitorImpressList: List<Any>,
    val monitorType: Any,
    val pic: String,
    val pid: Any,
    val program: Any,
    val requestId: String,
    val s_ctrp: String,
    val scm: String,
    val showAdTag: Boolean,
    val showContext: String,
    val song: Any,
    val targetId: Long,
    val targetType: Int,
    val titleColor: String,
    val typeTitle: String,
    val url: String,
    val video: Any
)

data class ExtMonitor(
    val monitorClick: String,
    val monitorImpress: String,
    val monitorType: String
)

data class ExtMonitorInfo(
    val aliAdId: String,
    val dspId: String,
    val extMonitor: Any,
    val monitorType: String,
    val needClickPosInfo: Boolean,
    val podcastInfo: Any,
    val reqId: String
)

data class Button(
    val action: String,
    val actionType: String,
    val biData: Any,
    val iconUrl: Any,
    val text: String
)