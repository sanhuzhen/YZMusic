package com.sanhuzhen.module.songlist.network

import com.sanhuzhen.lib.net.RetrofitRequest
import com.sanhuzhen.module.songlist.api.ApiService
import com.sanhuzhen.module.songlist.bean.MvData
import com.sanhuzhen.module.songlist.bean.SimiSingerData
import com.sanhuzhen.module.songlist.bean.SingerDesData
import com.sanhuzhen.module.songlist.bean.SingerDetailData
import com.sanhuzhen.module.songlist.bean.SongListData
import com.sansHuzsHen.module.songlist.bean.SongData
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

    fun getSingerList(id: String): Observable<SingerDetailData>{
        return apiService.getSingerDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSingerDesc(id: String): Observable<SingerDesData>{
        return apiService.getSingerDes(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getSimiSinger(id: String): Observable<SimiSingerData>{
        return apiService.getSimiArtist(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSingerHotSong(id: String): Observable<SongData>{
        return apiService.getSingerHotSong(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSingerMv(id: String): Observable<MvData>{
        return apiService.getSingerMv(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}