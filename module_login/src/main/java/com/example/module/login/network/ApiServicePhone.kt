package com.example.module.login.network

import com.example.module.login.bean.MyData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicePhone {
    @GET("/login/cellphone")
    fun getSearchData(
        @Query("phone")phone:String,
        @Query("password")password:String
    ): Observable<MyData>
}