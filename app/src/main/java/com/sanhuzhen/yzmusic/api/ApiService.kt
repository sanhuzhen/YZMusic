package com.sanhuzhen.yzmusic.api

import com.sanhuzhen.yzmusic.bean.ExtInfo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("/homepage/block/page")
    fun getBanner():Observable<ExtInfo>
}