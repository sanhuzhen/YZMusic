package com.sanhuzhen.module.songlist.network

import com.sanhuzhen.lib.net.RetrofitRequest
import com.sanhuzhen.module.songlist.api.ApiService
import com.sanhuzhen.module.songlist.bean.Song
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object Repository {

    private val apiService = RetrofitRequest.create(ApiService::class.java)

    fun getPlayList(id: String,limit:Int,offset:Int): Observable<Song>{
        return apiService.getSongList(id,limit,offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}