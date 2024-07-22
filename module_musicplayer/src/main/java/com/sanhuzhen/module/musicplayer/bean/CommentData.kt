package com.sanhuzhen.module.musicplayer.bean

data class CommentData(
    val code: Long,
    val `data`: mData,
    val message: String
)

data class mData(
    val bottomAction: Any,
    val comments: List<Comment>,
    val commentsTitle: String,
    val currentComment: Any,
    val currentCommentTitle: String,
    val cursor: String,
    val expandCount: Long,
    val hasMore: Boolean,
    val likeAnimation: LikeAnimation,
    val newReplyExpGroupName: String,
    val sortType: Long,
    val sortTypeList: List<SortType>,
    val style: String,
    val totalCount: Long
)

data class Comment(
    val args: Any,
    val beReplied: Any,
    val bottomTags: List<Any>,
    val commentId: Long,
    val commentLocationType: Long,
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
    val likedCount: Long,
    val medal: Any,
    val musicianSayAirborne: Any,
    val needDisplayTime: Boolean,
    val outShowComments: Any,
    val owner: Boolean,
    val parentCommentId: Long,
    val pendantData: PendantData,
    val pickInfo: Any,
    val privacy: Long,
    val repliedMark: Boolean,
    val replyCount: Long,
    val resourceSpecialType: Any,
    val richContent: Any,
    val showFloorComment: ShowFloorComment,
    val source: Any,
    val status: Long,
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
    val sortType: Long,
    val sortTypeName: String,
    val target: String
)

data class CommentVideoVO(
    val allowCreation: Boolean,
    val creationOrpheusUrl: Any,
    val forbidCreationText: String,
    val playOrpheusUrl: Any,
    val showCreationEntrance: Boolean,
    val videoCount: Long
)

data class Decoration(
    val repliedByAuthorCount: Long
)

class ExtInfo

data class IpLocation(
    val ip: Any,
    val location: String,
    val userId: Any
)

class LikeAnimationMap

data class PendantData(
    val id: Long,
    val imageUrl: String
)

data class ShowFloorComment(
    val comments: Any,
    val replyCount: Long,
    val showReplyCount: Boolean,
    val target: Any,
    val topCommentIds: Any
)

data class Tag(
    val contentDatas: List<Any>,
    val contentPicDatas: List<Any>,
    val datas: List<Any>,
    val extDatas: List<Any>,
    val relatedCommentIds: Any
)

data class User(
    val anonym: Long,
    val authStatus: Long,
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
    val userType: Long,
    val vipRights: VipRights,
    val vipType: Long
)

data class VipRights(
    val associator: Associator,
    val musicPackage: MusicPackage,
    val redVipAnnualCount: Long,
    val redVipLevel: Long,
    val redplus: Any,
    val relationType: Long
)

data class Associator(
    val iconUrl: String,
    val rights: Boolean,
    val vipCode: Long
)

data class MusicPackage(
    val iconUrl: String,
    val rights: Boolean,
    val vipCode: Long
)

data class AnimationConfigMap(
    val COMMENT_AREA: List<Any>,
    val EVENT_FEED: List<Any>,
    val INPUT: List<Any>,
    val MOMENT: List<Any>
)