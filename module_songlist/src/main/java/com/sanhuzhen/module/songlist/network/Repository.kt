package com.sanhuzhen.module.songlist.network

import com.sanhuzhen.lib.net.RetrofitRequest
import com.sanhuzhen.module.songlist.api.ApiService
import com.sanhuzhen.module.songlist.bean.SongListData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object Repository {

    private val apiService = RetrofitRequest.create(ApiService::class.java)

    fun getPlayList(id: String): Observable<SongListData>{
        return apiService.getSongList(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}