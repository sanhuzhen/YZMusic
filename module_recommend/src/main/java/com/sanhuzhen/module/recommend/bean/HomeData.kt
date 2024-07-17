package com.sanhuzhen.module.recommend.bean


data class HomeData(
    val code: Int,
    val `data`: Data
)

data class Data(
    val blocks: List<Block>,
)


data class Block(
    val creatives: List<Creative>,
    val extInfo: ExtInfo,
    val blockCode: String,
    val showType: String,
    val uiElement: UiElement
)

data class Creative(
    val creativeId: String,
    val creativeType: String,//create的类型
    val resources: List<Resource>
)


data class ExtInfo(
    val banners: List<Banner>,
)

data class Resource(
    val resourceExtInfo: ResourceExtInfo,
    val resourceId: String,
    val uiElement: UiElement
)

data class ResourceExtInfo(
    val artists: List<Artist>,
    val commentSimpleData: CommentSimpleData,
    val playCount: Long,
    val songData: SongData,
    )

data class UiElement(
    val image: Image,
    val labelTexts: List<String>,
    val mainTitle: MainTitle,
    val rcmdShowType: String,
    val subTitle: SubTitle
)

data class Artist(
    val id: Long,
    val img1v1Url: String,
    val name: String,
    val picUrl: String,
)

data class CommentSimpleData(
    val commentId: Long,
    val content: String,
    val threadId: String,
    val userId: Int,
    val userName: String
)

data class SongData(
    val album: Album,
    val artists: List<Artist>,
    val id: Long,
    val name: String, )

data class Album(
    val artist: Artist,
    val blurPicUrl: String,
    val id: Long,
    val name: String,
    val picUrl: String,
    val type: String
)

data class Image(

    val imageUrl: String,
    val imageUrl2: String,
    val title: String
)

data class MainTitle(
    val title: String
)

data class SubTitle(
    val targetUrl: String,
    val title: String,
    val titleId: String,
    val titleType: String
)

data class Banner(
    val pic: String,//banner图片
    val titleColor: String,//banner标题颜色
    val typeTitle: String,//banner标题
    val url: String,//banner链接
)