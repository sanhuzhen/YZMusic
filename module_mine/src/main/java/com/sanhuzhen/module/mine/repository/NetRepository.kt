package com.sanhuzhen.module.mine.repository

import com.sanhuzhen.lib.net.RetrofitRequest
import com.sanhuzhen.module.mine.bean.BaseData
import com.sanhuzhen.module.mine.bean.FavouriteData
import com.sanhuzhen.module.mine.bean.HistoryData
import com.sanhuzhen.module.mine.bean.PLData
import com.sanhuzhen.module.mine.network.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object NetRepository {
    val apiService = RetrofitRequest.create(ApiService::class.java)
    fun getSearchData(uid:Long): Observable<BaseData> {
        return apiService.getSearchData(uid)
            //指定被观察者线程，网络请求所在线程
            .subscribeOn(Schedulers.io())
            //指定观察者线程，把数据返回到主线程用来更新。
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getRecordData(uid: Long,type:Int): Observable<HistoryData> {
        return apiService.getRecordData(uid,type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getPLData(uid: Long): Observable<PLData> {
        return apiService.getPLData(uid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getFavouriteData(id: Long): Observable<FavouriteData> {
        return apiService.getFavouriteData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}