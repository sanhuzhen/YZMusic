package com.sanhuzhen.module.mvplayer.api

import com.sanhuzhen.module.mvplayer.bean.CommentData
import com.sanhuzhen.module.mvplayer.bean.MvDetailData
import com.sanhuzhen.module.mvplayer.bean.OtherData
import com.sanhuzhen.module.mvplayer.bean.SingerData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/mv/url")
    fun getMvUrl(
        @Query("id") id: String,
    ): Observable<MvDetailData>
    @GET("/mv/detail")
    fun getSinger(
        @Query("mvid") mvid: String,
    ): Observable<SingerData>
    @GET("/comment/new")
    fun getComment(
        @Query("id") id: String,
        @Query("type") type: Int,
        @Query("sortType")sortType:Int
    ): Observable<CommentData>
    @GET("/mv/detail/info")
    fun getMvDetail(
        @Query("mvid") mvid: String,
    ): Observable<OtherData>
}