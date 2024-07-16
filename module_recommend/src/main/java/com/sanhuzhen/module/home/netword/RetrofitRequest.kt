package com.sanhuzhen.module.home.netword

import com.sanhuzhen.module.home.api.ApiService
import com.sanhuzhen.module.home.bean.HomeData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRequest {
    private val BASE_URL = "http://82.156.18.110:3000/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    fun getHomeData(): Observable<HomeData>{
        return apiService.getHomeData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}