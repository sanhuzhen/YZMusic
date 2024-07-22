package com.sanhuzhen.module.hot.network

import com.sanhuzhen.module.hot.bean.HotListData
import com.sanhuzhen.module.hot.bean.SingerData
import com.sanhuzhen.module.hot.bean.SongListData
import com.sanhuzhen.module.hot.bean.TopListData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("toplist/artist")
    fun getSingerData(): Observable<SingerData>
    @GET("toplist")
    fun getTopListData():Observable<TopListData>
    @GET("top/playlist/highquality")
    fun getHotListData(
        @Query("limit") limit:Int
    ):Observable<HotListData>
    @GET("/playlist/detail")
    fun getSongData(
        @Query("id") id:Long
    ):Observable<SongListData>
}