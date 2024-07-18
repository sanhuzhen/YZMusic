package com.sanhuzhen.module.mvplayer.api

import com.sanhuzhen.module.mvplayer.bean.MvDetailData
import com.sanhuzhen.module.mvplayer.bean.MvDetailInfoData
import com.sanhuzhen.module.mvplayer.bean.MvUrlData
import com.sanhuzhen.module.mvplayer.bean.SimiMvData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //获取mv地址
    @GET("mv/url")
    fun getMvUrl(@Query("id")id: String) : Observable<MvUrlData>
    //获取mv详情
    @GET("mv/detail")
    fun getMvDetail(@Query("mvid")id: String) : Observable<MvDetailData>
    //获取 mv 点赞转发评论数数据
    @GET("mv/detail/info")
    fun getMvDetailInfo(@Query("mvid")id: String) : Observable<MvDetailInfoData>
    //相似 mv
    @GET("simi/mv")
    fun getSimiMv(@Query("mvid")id: String) : Observable<SimiMvData>
}