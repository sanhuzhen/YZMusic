package com.sanhuzhen.module.musicplayer.api

import com.sanhuzhen.module.musicplayer.bean.MusicUrlData
import com.sanhuzhen.module.musicplayer.bean.MusicUsdData
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
    fun checkMusic(@Query("id") id: String): Observable<MusicUsdData>

    //获取歌曲详情
    @GET("song/detail")
    fun getSongDetail(@Query("ids") ids: String): Observable<SongDetailData>
}