package com.sanhuzhen.module.songlist.bean

data class SingerDesData(
    val briefDesc: String,
    val code: Int,
    val count: Int,
    val introduction: List<Introduction>,
    val topicData: List<TopicData>
)

data class Introduction(
    val ti: String,
    val txt: String
)

data class TopicData(
    val addTime: Long,
    val categoryId: Int,
    val categoryName: String,
    val commentCount: Int,
    val commentThreadId: String,
    val coverUrl: String,
    val creator: mCreator,
    val id: Int,
    val liked: Boolean,
    val likedCount: Int,
    val mainTitle: String,
    val memo: Any,
    val number: Int,
    val readCount: Int,
    val recmdContent: String,
    val recmdTitle: String,
    val rectanglePicUrl: String,
    val relatedResource: Any,
    val reward: Boolean,
    val rewardCount: Int,
    val rewardMoney: Int,
    val seriesId: Int,
    val shareContent: String,
    val shareCount: Int,
    val showComment: Boolean,
    val showRelated: Boolean,
    val summary: String,
    val tags: List<String>,
    val title: String,
    val topic: Topic,
    val url: String,
    val wxTitle: String
)

data class mCreator(
    val accountStatus: Int,
    val accountType: Int,
    val anchor: Boolean,
    val authStatus: Int,
    val authenticated: Boolean,
    val authenticationTypes: Int,
    val authority: Int,
    val avatarDetail: Any,
    val avatarImgId: Long,
    val avatarUrl: String,
    val backgroundImgId: Long,
    val backgroundUrl: String,
    val birthday: Long,
    val city: Int,
    val createTime: Long,
    val defaultAvatar: Boolean,
    val description: String,
    val detailDescription: String,
    val djStatus: Int,
    val expertTags: List<String>,
    val experts: Experts,
    val followed: Boolean,
    val gender: Int,
    val lastLoginIP: String,
    val lastLoginTime: Long,
    val locationStatus: Int,
    val mutual: Boolean,
    val nickname: String,
    val province: Int,
    val remarkName: Any,
    val shortUserName: String,
    val signature: String,
    val userId: Int,
    val userName: String,
    val userType: Int,
    val vipType: Int,
    val viptypeVersion: Long
)

data class Topic(
    val adInfo: String,
    val addTime: Long,
    val auditStatus: Int,
    val auditTime: Int,
    val auditor: String,
    val categoryId: Int,
    val content: List<Content>,
    val cover: Long,
    val delReason: String,
    val fromBackend: Boolean,
    val headPic: Int,
    val hotScore: Int,
    val id: Int,
    val mainTitle: String,
    val memo: Any,
    val number: Int,
    val pubImmidiatly: Boolean,
    val pubTime: Long,
    val readCount: Int,
    val recomdContent: String,
    val recomdTitle: String,
    val rectanglePic: Long,
    val reward: Boolean,
    val seriesId: Int,
    val shareContent: String,
    val showComment: Boolean,
    val showRelated: Boolean,
    val startText: String,
    val status: Int,
    val summary: String,
    val tags: List<String>,
    val title: String,
    val updateTime: Long,
    val userId: Int,
    val wxTitle: String
)

data class Experts(
    val `1`: String
)

data class Content(
    val content: String,
    val id: Long,
    val type: Int
)