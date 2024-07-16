package com.example.module.search.network

import com.example.module.search.bean.ArtistsData
import com.example.module.search.bean.MusicData
import com.example.module.search.bean.MvData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/search")
    fun getSearchMusicData(
        @Query("keywords")keywords:String,
        @Query("type")type:Int
    ): Observable<MusicData>
    @GET("/search")
    fun getSearchMvData(
        @Query("keywords")keywords:String,
        @Query("type")type:Int
    ): Observable<MvData>
    @GET("/search")
    fun getSearchArtistsData(
        @Query("keywords")keywords:String,
        @Query("type")type:Int
    ): Observable<ArtistsData>
}