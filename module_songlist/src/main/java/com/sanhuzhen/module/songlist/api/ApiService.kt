package com.sanhuzhen.module.songlist.api

import com.sanhuzhen.module.songlist.bean.Song
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/playlist/track/all")
    fun getSongList(@Query("id") id:String,@Query("limit") limit:Int,@Query("offset") offset:Int):Observable<Song>
}