package com.sanhuzhen.yzmusic.api

import com.sanhuzhen.yzmusic.bean.HomeData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    //得到首页数据
    @GET("/homepage/block/page")
    fun getBlocks():Observable<HomeData>
}