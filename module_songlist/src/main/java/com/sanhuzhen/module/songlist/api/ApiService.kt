package com.sanhuzhen.module.songlist.api

import com.sanhuzhen.module.songlist.bean.SingerDesData
import com.sanhuzhen.module.songlist.bean.SingerDetailData
import com.sanhuzhen.module.songlist.bean.SongListData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlist/detail")
    fun getSongList(@Query("id") id:String):Observable<SongListData>

    //获取歌手详情
    @GET("artist/detail")
    fun getSingerDetail(@Query("id") id:String):Observable<SingerDetailData>

    //获取歌手描述
    @GET("artist/desc")
    fun getSingerDes(@Query("id") id:String):Observable<SingerDesData>
}