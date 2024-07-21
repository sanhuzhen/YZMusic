package com.sanhuzhen.module.hot.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.module.hot.bean.HotListData
import com.sanhuzhen.module.hot.bean.SingerData
import com.sanhuzhen.module.hot.bean.SongListData
import com.sanhuzhen.module.hot.bean.TopListData
import com.sanhuzhen.module.hot.repository.NetRepository
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class BaseViewModel :ViewModel(){
    private val singer=MutableLiveData<SingerData>()
    val mSinger:MutableLiveData<SingerData>
        get() = singer
    private val topList=MutableLiveData<TopListData>()
    val mTopList:MutableLiveData<TopListData>
        get() = topList
    private val hotList=MutableLiveData<HotListData>()
    val mHotList:MutableLiveData<HotListData>
        get() = hotList
    private val songList=MutableLiveData<SongListData>()
    val mSongList:MutableLiveData<SongListData>
        get() = songList
    fun getSinger(){
        NetRepository.getSingerData().subscribe(object : Observer<SingerData> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d("SingerError","${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: SingerData) {
                Log.d("SingerData","${t}")
                singer.postValue(t)
            }
        })
    }
    fun getTopList(){
        NetRepository.getTopListData().subscribe(object :Observer<TopListData>{
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d("TopLIstError","${e.message}")
            }

            override fun onComplete() {
            }

            override fun onNext(t: TopListData) {
                Log.d("TopListData","${t}")
                topList.postValue(t)
            }

        })
    }
    fun getHotList(limit:Int){
        NetRepository.getHotListData(limit).subscribe(object :Observer<HotListData>{
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d("HotListError","${e.message}")
            }

            override fun onComplete() {
            }

            override fun onNext(t: HotListData) {
                Log.d("HotList","${t}")
                mHotList.postValue(t)
            }
        })
    }
    fun getSongList(id:Long){
        NetRepository.getSongData(id).subscribe(object :Observer<SongListData>{
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d("SongListError","${e.message}")
            }

            override fun onComplete() {
            }

            override fun onNext(t: SongListData) {
                Log.d("SongList","${t}")
               songList.postValue(t)
            }
        })
    }
}