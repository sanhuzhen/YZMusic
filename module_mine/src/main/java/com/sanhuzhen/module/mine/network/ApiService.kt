package com.sanhuzhen.module.mine.network

import com.sanhuzhen.module.mine.bean.BaseData
import com.sanhuzhen.module.mine.bean.FavouriteData
import com.sanhuzhen.module.mine.bean.HistoryData
import com.sanhuzhen.module.mine.bean.PLData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("user/detail")
    fun getSearchData(
        @Query("uid")uid:Long
    ): Observable<BaseData>
    @GET("/user/record")
    fun getRecordData(
        @Query("uid")uid:Long,
        @Query("type")type:Int
    ): Observable<HistoryData>
    @GET("user/playlist")
    fun getPLData(
        @Query("uid")uid:Long
    ): Observable<PLData>
    @GET("/playlist/detail")
    fun getFavouriteData(
        @Query("id")id:Long
    ): Observable<FavouriteData>
}