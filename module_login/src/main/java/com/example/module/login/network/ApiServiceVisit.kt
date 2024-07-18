package com.example.module.login.network

import com.example.module.login.bean.MyData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiServiceVisit {
    @GET("/register/anonimous")
    fun getSearchVisitData(): Observable<MyData>
}