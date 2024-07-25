package com.sanhuzhen.module.musicplayer.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sanhuzhen.lib.net.RetrofitRequest
import com.sanhuzhen.module.musicplayer.api.ApiService
import com.sanhuzhen.module.musicplayer.bean.Comment
import com.sanhuzhen.module.musicplayer.bean.MusicUrlData
import com.sanhuzhen.module.musicplayer.bean.MusicUsedData
import com.sanhuzhen.module.musicplayer.bean.SongDetailData
import com.sanhuzhen.module.musicplayer.bean.SongWordData
import com.sanhuzhen.module.musicplayer.helper.CommentPagingSource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow

object NetWork {
    private val apiService = RetrofitRequest.create(ApiService::class.java)
    //    val apiService = RetrofitRequest.create(ApiService::class.java)
    fun getMusicUrl(id: String): Observable<MusicUrlData> {
        return apiService.getMusicUrl(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun checkMusic(id: String): Observable<MusicUsedData> {
        return apiService.checkMusic(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getSongDetail(ids: String): Observable<SongDetailData> {
        return apiService.getSongDetail(ids)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    /**
     * 由于Paging3需要使用flow，所以获取评论使用协程来请求数据(but，我协程只学习了相关用法，并未深入了解)
     */
    fun getCommentsFlow(type: String, id: String, sortType: String): Flow<PagingData<Comment>> {
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = { CommentPagingSource(apiService, type, id, sortType) }
        ).flow
    }

    fun getSongLyric(id: String): Observable<SongWordData> {
        return apiService.getSongLyric(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}