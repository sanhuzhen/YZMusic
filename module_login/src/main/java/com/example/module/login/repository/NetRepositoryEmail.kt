package com.example.module.login.repository

import com.example.module.login.bean.MyData
import com.example.module.login.network.ApiServiceEmail
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetRepositoryEmail {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://baobab.kaiyanapp.com/api/")
        .addConverterFactory(GsonConverterFactory.create())//这里添加GSON的converter,后面把数据解析成对象要用。
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiServiceEmail::class.java)


    fun getSearchData(email: String, password: String): Observable<MyData> {
        return apiService.getSearchData(email, password)
            //指定被观察者线程，网络请求所在线程
            .subscribeOn(Schedulers.io())
            //指定观察者线程，把数据返回到主线程用来更新。
            .observeOn(AndroidSchedulers.mainThread())

    }
}