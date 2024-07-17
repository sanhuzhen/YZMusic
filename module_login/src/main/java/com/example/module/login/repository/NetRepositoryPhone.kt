package com.example.module.login.repository

import com.example.module.login.bean.MyData
import com.example.module.login.network.ApiServiceCaptchaSend
import com.example.module.login.network.ApiServiceEmail
import com.example.module.login.network.ApiServicePhone
import com.example.module.login.network.ApiServiceVisit
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetRepositoryPhone {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://82.156.18.110:3000")
        .addConverterFactory(GsonConverterFactory.create())//这里添加GSON的converter,后面把数据解析成对象要用。
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val apiServicePhone = retrofit.create(ApiServicePhone::class.java)
    private val apiServiceSend = retrofit.create(ApiServiceCaptchaSend::class.java)
    private val apiServiceVerify = retrofit.create(ApiServiceCaptchaSend::class.java)
    private val apiServiceEmail = retrofit.create(ApiServiceEmail::class.java)
    private val apiServiceVisit= retrofit.create(ApiServiceVisit::class.java)


    fun getSearchDataEmail(email: String, password: String): Observable<MyData> {
        return apiServiceEmail.getSearchData(email, password)
            //指定被观察者线程，网络请求所在线程
            .subscribeOn(Schedulers.io())
            //指定观察者线程，把数据返回到主线程用来更新。
            .observeOn(AndroidSchedulers.mainThread())

    }


    fun getSearchDataVerify(phone: String, captcha:String): Observable<MyData> {
        return apiServiceVerify.getSearchDataVerify(phone, captcha)
            //指定被观察者线程，网络请求所在线程
            .subscribeOn(Schedulers.io())
            //指定观察者线程，把数据返回到主线程用来更新。
            .observeOn(AndroidSchedulers.mainThread())

    }


    fun getSearchDataSend(phone: String ): Observable<MyData> {
        return apiServiceSend.getSearchDataSend(phone)
            //指定被观察者线程，网络请求所在线程
            .subscribeOn(Schedulers.io())
            //指定观察者线程，把数据返回到主线程用来更新。
            .observeOn(AndroidSchedulers.mainThread())


    }
        fun getSearchDataPhone(phone: String, password: String): Observable<MyData> {
            return apiServicePhone.getSearchData(phone, password)
                //指定被观察者线程，网络请求所在线程
                .subscribeOn(Schedulers.io())
                //指定观察者线程，把数据返回到主线程用来更新。
                .observeOn(AndroidSchedulers.mainThread())

        }
    fun getSearchDataVisit(): Observable<MyData> {
        return apiServiceVisit.getSearchVisitData()
            //指定被观察者线程，网络请求所在线程
            .subscribeOn(Schedulers.io())
            //指定观察者线程，把数据返回到主线程用来更新。
            .observeOn(AndroidSchedulers.mainThread())

    }
}