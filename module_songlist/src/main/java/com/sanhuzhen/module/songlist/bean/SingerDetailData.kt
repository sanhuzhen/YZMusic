package com.sanhuzhen.module.songlist.bean

data class SingerDetailData(
    val code: Long,
    val `data`: Data,
    val message: String
)

data class Data(
    val artist: mArtist,
    val blacklist: Boolean,
    val eventCount: Long,
    val identify: Identify,
    val preferShow: Long,
    val secondaryExpertIdentiy: List<SecondaryExpertIdentiy>,
    val showPriMsg: Boolean,
    val user: User,
    val videoCount: Long,
    val vipRights: VipRights
)

data class mArtist(
    val albumSize: Long,
    val alias: List<String>,
    val avatar: String,
    val briefDesc: String,
    val cover: String,
    val id: Long,
    val identifyTag: List<String>,
    val identities: List<String>,
    val musicSize: Long,
    val mvSize: Long,
    val name: String,
    val rank: Rank,
    val transNames: List<String>
)

data class Identify(
    val actionUrl: String,
    val imageDesc: String,
    val imageUrl: String
)

data class SecondaryExpertIdentiy(
    val expertIdentiyCount: Long,
    val expertIdentiyId: Long,
    val expertIdentiyName: String
)

data class User(
    val accountStatus: Long,
    val accountType: Long,
    val anchor: Boolean,
    val authStatus: Long,
    val authenticated: Boolean,
    val authenticationTypes: Long,
    val authority: Long,
    val avatarDetail: AvatarDetail,
    val avatarImgId: Long,
    val avatarUrl: String,
    val backgroundImgId: Long,
    val backgroundUrl: String,
    val birthday: Long,
    val city: Long,
    val createTime: Long,
    val defaultAvatar: Boolean,
    val description: String,
    val detailDescription: String,
    val djStatus: Long,
    val expertTags: Any,
    val experts: Any,
    val followed: Boolean,
    val gender: Long,
    val lastLoginIP: String,
    val lastLogLongime: Long,
    val locationStatus: Long,
    val mutual: Boolean,
    val nickname: String,
    val province: Long,
    val remarkName: Any,
    val shortUserName: String,
    val signature: String,
    val userId: Long,
    val userName: String,
    val userType: Long,
    val vipType: Long
)

data class VipRights(
    val now: Long,
    val oldProtocol: Boolean,
    val redVipAnnualCount: Long,
    val redVipLevel: Long,
    val rightsInfoDetailDtoList: List<RightsInfoDetailDto>
)

data class Rank(
    val rank: Long,
    val type: Long
)

data class AvatarDetail(
    val identityIconUrl: String,
    val identityLevel: Long,
    val userType: Long
)

data class RightsInfoDetailDto(
    val dynamicIconUrl: Any,
    val expireTime: Long,
    val iconUrl: Any,
    val sign: Boolean,
    val signDeduct: Boolean,
    val signIap: Boolean,
    val signIapDeduct: Boolean,
    val vipCode: Long,
    val vipLevel: Long
)