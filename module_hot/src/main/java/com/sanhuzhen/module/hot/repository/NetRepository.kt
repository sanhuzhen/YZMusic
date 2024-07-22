package com.sanhuzhen.module.hot.repository

import com.sanhuzhen.lib.net.RetrofitRequest
import com.sanhuzhen.module.hot.bean.HotListData
import com.sanhuzhen.module.hot.bean.SingerData
import com.sanhuzhen.module.hot.bean.SongListData
import com.sanhuzhen.module.hot.bean.TopListData
import com.sanhuzhen.module.hot.network.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object NetRepository {
    private val apiService = RetrofitRequest.create(ApiService::class.java)
    fun getSingerData(): Observable<SingerData> {
        return apiService.getSingerData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getTopListData():Observable<TopListData>{
        return apiService.getTopListData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getHotListData(limit:Int):Observable<HotListData>{
        return apiService.getHotListData(limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getSongData(id:Long):Observable<SongListData>{
        return apiService.getSongData(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}