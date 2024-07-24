package com.sanhuzhen.module.songlist.api

import com.sanhuzhen.module.songlist.bean.MvData
import com.sanhuzhen.module.songlist.bean.SimiSingerData
import com.sanhuzhen.module.songlist.bean.SingerDesData
import com.sanhuzhen.module.songlist.bean.SingerDetailData
import com.sanhuzhen.module.songlist.bean.SongListData
import com.sansHuzsHen.module.songlist.bean.SongData
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

    //获取相似歌手
    @GET("simi/artist")
    fun getSimiArtist(@Query("id") id:String):Observable<SimiSingerData>

    //获取歌手热门歌曲
    @GET("artist/top/song")
    fun getSingerHotSong(@Query("id") id:String):Observable<SongData>

    //获取歌手mv
    @GET("artist/mv")
    fun getSingerMv(@Query("id") id:String):Observable<MvData>
}