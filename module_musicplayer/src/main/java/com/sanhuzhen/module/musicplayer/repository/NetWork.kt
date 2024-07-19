package com.sanhuzhen.module.musicplayer.repository

import com.sanhuzhen.lib.net.RetrofitRequest
import com.sanhuzhen.module.musicplayer.api.ApiService
import com.sanhuzhen.module.musicplayer.bean.MusicUrlData
import com.sanhuzhen.module.musicplayer.bean.MusicUsdData
import com.sanhuzhen.module.musicplayer.bean.SongDetailData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object NetWork {
    private val apiService = RetrofitRequest.create(ApiService::class.java)
//    val apiService = RetrofitRequest.create(ApiService::class.java)
    fun getMusicUrl(id: String): Observable<MusicUrlData> {
        return apiService.getMusicUrl(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun checkMusic(id: String): Observable<MusicUsdData> {
        return apiService.checkMusic(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getSongDetail(ids: String): Observable<SongDetailData> {
        return apiService.getSongDetail(ids)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}