package com.sanhuzhen.module.home.network

import com.sanhuzhen.lib.net.RetrofitRequest
import com.sanhuzhen.module.home.api.ApiService
import com.sanhuzhen.module.home.bean.HomeData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object RetrofitRequest {
    private val apiService = RetrofitRequest.create(ApiService::class.java)

    fun getHomeData(): Observable<HomeData>{
        return apiService.getHomeData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}