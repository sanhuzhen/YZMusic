package com.sanhuzhen.module.recommend.api

import com.sanhuzhen.module.recommend.bean.HomeData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    //首页数据
    @GET("homepage/block/page")
    fun getHomeData(): Observable<HomeData>
}