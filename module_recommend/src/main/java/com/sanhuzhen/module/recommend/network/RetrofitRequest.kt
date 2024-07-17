package com.sanhuzhen.module.recommend.network

import com.sanhuzhen.lib.net.RetrofitRequest
import com.sanhuzhen.module.recommend.api.ApiService
import com.sanhuzhen.module.recommend.bean.HomeData
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