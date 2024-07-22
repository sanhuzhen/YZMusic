package com.sanhuzhen.module.musicplayer.api

import com.sanhuzhen.module.musicplayer.bean.CommentData
import com.sanhuzhen.module.musicplayer.bean.MusicUrlData
import com.sanhuzhen.module.musicplayer.bean.MusicUsedData
import com.sanhuzhen.module.musicplayer.bean.SongDetailData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //获取音乐url
    @GET("song/url")
    fun getMusicUrl(
        @Query("id") id: String
    ): Observable<MusicUrlData>

    //音乐是否可用
    @GET("check/music")
//    suspend fun checkMusic(@Query("id") id: String): MusicUsdData
    fun checkMusic(@Query("id") id: String): Observable<MusicUsedData>

    //获取歌曲详情
    @GET("song/detail")
    fun getSongDetail(@Query("ids") ids: String): Observable<SongDetailData>

    //获取歌曲评论
    @GET("comment/new")
    suspend fun getSongComment(
        @Query("type") type: String,
        @Query("id") id: String,
        @Query("sortType") sortType: String,
        @Query("cursor") cursor: String,
        @Query("pageSize") pageSize: Int,
        @Query("pageNo") pageNo: Int
    ): CommentData
}