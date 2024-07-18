package com.sanhuzhen.module.mvplayer.repository

import com.sanhuzhen.lib.net.RetrofitRequest
import com.sanhuzhen.module.mvplayer.api.ApiService
import com.sanhuzhen.module.mvplayer.bean.Data
import com.sanhuzhen.module.mvplayer.bean.MvDetailData
import com.sanhuzhen.module.mvplayer.bean.MvUrlData
import com.sanhuzhen.module.mvplayer.bean.SimiMvData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object NetWork {
    private val apiService = RetrofitRequest.create(ApiService::class.java)

    fun getMvUrl(id: String) : Observable<MvUrlData>{
        return apiService.getMvUrl(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getMvDetail(mvid: String) : Observable<MvDetailData>{
        return apiService.getMvDetail(mvid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getSimiMv(mvid: String) : Observable<SimiMvData>{
        return apiService.getSimiMv(mvid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}