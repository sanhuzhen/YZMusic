package com.sanhuzhen.module.mvplayer.repository

import com.sanhuzhen.lib.net.RetrofitRequest
import com.sanhuzhen.module.mvplayer.api.ApiService
import com.sanhuzhen.module.mvplayer.bean.CommentData
import com.sanhuzhen.module.mvplayer.bean.MvDetailData
import com.sanhuzhen.module.mvplayer.bean.OtherData
import com.sanhuzhen.module.mvplayer.bean.SingerData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object NetRepository {
    private val apiService= RetrofitRequest.create(ApiService::class.java)
    fun getMvUrl(id:String): Observable<MvDetailData> {
        return apiService.getMvUrl(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getMVSinger(mvid:String):Observable<SingerData>{
        return apiService.getSinger(mvid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getComment(id:String):Observable<CommentData>{
        return apiService.getComment(id,1,3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getOther(mvid: String):Observable<OtherData>{
        return apiService.getMvDetail(mvid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}