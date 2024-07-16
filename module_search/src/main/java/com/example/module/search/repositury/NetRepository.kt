package com.example.module.search.repositury

import com.example.module.search.bean.ArtistsData
import com.example.module.search.bean.MusicData
import com.example.module.search.bean.MvData
import com.example.module.search.network.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://1258656679-dk116gec67-gz.scf.tencentcs.com")
        .addConverterFactory(GsonConverterFactory.create())//这里添加GSON的converter,后面把数据解析成对象要用。
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val apiServiceMusic = retrofit.create(ApiService::class.java)
    private val apiServiceMv = retrofit.create(ApiService::class.java)
    private val apiServiceArtists= retrofit.create(ApiService::class.java)


    fun getSearchMusicData(keywords: String, type: Int): Observable<MusicData> {
        return apiServiceMusic.getSearchMusicData(keywords, type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getSearchMvData(keywords: String, type: Int): Observable<MvData> {
        return apiServiceMv.getSearchMvData(keywords, type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getSearchArtistsData(keywords: String, type: Int): Observable<ArtistsData> {
        return apiServiceArtists.getSearchArtistsData(keywords, type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}