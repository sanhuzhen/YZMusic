package com.sanhuzhen.module.mvplayer.bean

 data class CommentData(
    val code: Int,
    val `data`: DataY,
    val message: String
)

data class DataY(
    val bottomAction: Any,
    val comments: List<Comment>,
    val commentsTitle: String,
    val currentComment: Any,
    val currentCommentTitle: String,
    val cursor: String,
    val expandCount: Int,
    val hasMore: Boolean,
    val likeAnimation: LikeAnimation,
    val newReplyExpGroupName: String,
    val sortType: Int,
    val sortTypeList: List<SortType>,
    val style: String,
    val totalCount: Int
)

data class Comment(
    val args: Any,
    val beReplied: Any,
    val bottomTags: List<Any>,
    val commentId: Long,
    val commentLocationType: Int,
    val commentVideoVO: CommentVideoVO,
    val content: String,
    val contentPicNosKey: Any,
    val contentPicUrl: Any,
    val contentResource: Any,
    val decoration: Decoration,
    val expressionUrl: Any,
    val extInfo: ExtInfo,
    val grade: Any,
    val hideSerialComments: Any,
    val hideSerialTips: Any,
    val ipLocation: IpLocation,
    val likeAnimationMap: LikeAnimationMap,
    val liked: Boolean,
    val likedCount: Int,
    val medal: Medal,
    val musicianSayAirborne: Any,
    val needDisplayTime: Boolean,
    val outShowComments: Any,
    val owner: Boolean,
    val parentCommentId: Int,
    val pendantData: PendantData,
    val pickInfo: Any,
    val privacy: Int,
    val repliedMark: Boolean,
    val replyCount: Int,
    val resourceSpecialType: Any,
    val richContent: String,
    val showFloorComment: ShowFloorComment,
    val source: Any,
    val status: Int,
    val tag: Tag,
    val tail: Any,
    val threadId: String,
    val time: Long,
    val timeStr: String,
    val topicList: Any,
    val track: String,
    val user: User,
    val userBizLevels: Any,
    val userNameplates: Any
)

data class LikeAnimation(
    val animationConfigMap: AnimationConfigMap,
    val version: Long
)

data class SortType(
    val sortType: Int,
    val sortTypeName: String,
    val target: String
)

data class CommentVideoVO(
    val allowCreation: Boolean,
    val creationOrpheusUrl: Any,
    val forbidCreationText: String,
    val playOrpheusUrl: Any,
    val showCreationEntrance: Boolean,
    val videoCount: Int
)

data class Decoration(
    val repliedByAuthorCount: Int
)

data class ExtInfo(
    val endpoint: Endpoint,
    val forwardEvent: Int
)

data class IpLocation(
    val ip: Any,
    val location: String,
    val userId: Long
)

class LikeAnimationMap

data class Medal(
    val detailPage: String,
    val wearPic: String
)

data class PendantData(
    val id: Int,
    val imageUrl: String
)

data class ShowFloorComment(
    val comments: Any,
    val replyCount: Int,
    val showReplyCount: Boolean,
    val target: Any,
    val topCommentIds: Any
)

data class Tag(
    val contentDatas: Any,
    val contentPicDatas: Any,
    val datas: Any,
    val extDatas: List<Any>,
    val relatedCommentIds: Any
)

data class User(
    val anonym: Int,
    val authStatus: Int,
    val avatarDetail: Any,
    val avatarUrl: String,
    val commonIdentity: Any,
    val encryptUserId: String,
    val expertTags: Any,
    val experts: Any,
    val followed: Boolean,
    val isHug: Boolean,
    val liveInfo: Any,
    val locationInfo: Any,
    val nickname: String,
    val relationTag: Any,
    val remarkName: Any,
    val socialUserId: Any,
    val target: Any,
    val userId: Long,
    val userType: Int,
    val vipRights: VipRights,
    val vipType: Int
)

data class Endpoint(
    val CHANNEL: String,
    val CLIENT_BUILD_VERSION: String,
    val CLIENT_SIGN: String,
    val CLIENT_TYPE: String,
    val CLIENT_VERSION: String,
    val CLIENT_VERSION_STR: String,
    val DEVICE_ID: String,
    val IP: String,
    val OSVER: String,
    val OS_TYPE: String,
    val PROXY_IP: String,
    val REFERER: String,
    val USER_AGENT: String,
    val X_REMOTE_PORT: String
)

data class VipRights(
    val associator: Associator,
    val musicPackage: MusicPackage,
    val redVipAnnualCount: Int,
    val redVipLevel: Int,
    val redplus: Any,
    val relationType: Int
)

data class Associator(
    val iconUrl: String,
    val rights: Boolean,
    val vipCode: Int
)

data class MusicPackage(
    val iconUrl: String,
    val rights: Boolean,
    val vipCode: Int
)

data class AnimationConfigMap(
    val COMMENT_AREA: List<Any>,
    val EVENT_FEED: List<Any>,
    val INPUT: List<Any>,
    val MOMENT: List<Any>
)