package com.example.module.login.network

import com.example.module.login.bean.MyData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceCaptchaSend {
    @GET("/captcha/sent")
    fun getSearchDataSend(
        @Query("phone")phone:String
    ): Observable<MyData>
    @GET("/captcha/verify")
    fun getSearchDataVerify(
        @Query("phone")phone:String,
        @Query("captcha")captcha:String
    ): Observable<MyData>
}