package com.sanhuzhen.module.songlist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.module.songlist.bean.SingerDesData
import com.sanhuzhen.module.songlist.bean.SingerDetailData
import com.sanhuzhen.module.songlist.bean.mArtist
import com.sanhuzhen.module.songlist.network.Repository
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class SingerViewModel: ViewModel() {

    private val _singerList = MutableLiveData<mArtist>()
    private val _singerDes = MutableLiveData<SingerDesData>()


    val singerList : LiveData<mArtist>
        get() = _singerList

    val singerDes : LiveData<SingerDesData>
        get() = _singerDes

    fun getSingerList(id:String){
        Repository.getSingerList(id).subscribe(object : Observer<SingerDetailData> {
            override fun onSubscribe(d: Disposable) {
            }
            override fun onNext(t: SingerDetailData) {
                _singerList.postValue(t.data.artist)
            }
            override fun onError(e: Throwable) {
                Log.e("Error","------ ${e.message}")
            }
            override fun onComplete() {
            }
        })
    }
    fun getSingerDes(id:String){
        Repository.getSingerDesc(id).subscribe(object : Observer<SingerDesData> {
            override fun onSubscribe(d: Disposable) {
            }
            override fun onNext(t: SingerDesData) {
                _singerDes.postValue(t)
            }
            override fun onError(e: Throwable) {
                Log.e("Error","------ ${e.message}")
            }
            override fun onComplete() {
            }
        })
    }
}