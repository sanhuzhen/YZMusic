package com.sanhuzhen.yzmusic.network

import com.sanhuzhen.yzmusic.api.ApiService
import com.sanhuzhen.yzmusic.bean.Data
import com.sanhuzhen.yzmusic.bean.HomeData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitRequest {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://1258656679-dk116gec67-gz.scf.tencentcs.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    fun getBlocks():Observable<HomeData>{
        return apiService.getBlocks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}