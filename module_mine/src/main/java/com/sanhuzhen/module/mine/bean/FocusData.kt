package com.sanhuzhen.module.mine.bean

data class FocusData(
    val follow: List<Follow>,
)

data class Follow(
    val avatarDetail: AvatarDetail,
    val avatarUrl: String,
    val nickname: String,
    val userId: Long,
    val vipRights: VipRights,
)

data class AvatarDetail(
    val identityIconUrl: String,
)

data class VipRights(
    val associator: Associator,
)

data class Associator(
    val iconUrl: String,
)