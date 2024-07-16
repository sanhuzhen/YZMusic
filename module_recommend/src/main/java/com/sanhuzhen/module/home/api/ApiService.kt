package com.sanhuzhen.module.home.api

import com.sanhuzhen.module.home.bean.HomeData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    //首页数据
    @GET("homepage/block/page")
    fun getHomeData(): Observable<HomeData>
}