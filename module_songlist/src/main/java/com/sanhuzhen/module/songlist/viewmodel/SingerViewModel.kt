package com.sanhuzhen.module.songlist.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.module.songlist.bean.Mv
import com.sanhuzhen.module.songlist.bean.MvData
import com.sanhuzhen.module.songlist.bean.SimiSingerData
import com.sanhuzhen.module.songlist.bean.SingerDesData
import com.sanhuzhen.module.songlist.bean.SingerDetailData
import com.sanhuzhen.module.songlist.bean.mArtist
import com.sanhuzhen.module.songlist.bean.sArtist
import com.sanhuzhen.module.songlist.network.Repository
import com.sansHuzsHen.module.songlist.bean.Song
import com.sansHuzsHen.module.songlist.bean.SongData
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class SingerViewModel: ViewModel() {

    private val _singerList = MutableLiveData<mArtist>()
    private val _singerDes = MutableLiveData<SingerDesData>()
    private val _simiSinger = MutableLiveData<List<sArtist>>()
    private val _singerHotSong = MutableLiveData<List<Song>>()
    private val _singerMv = MutableLiveData<List<Mv>>()


    val singerList : LiveData<mArtist>
        get() = _singerList

    val singerDes : LiveData<SingerDesData>
        get() = _singerDes

    val simiSinger : LiveData<List<sArtist>>
        get() = _simiSinger

    val singerHotSong : LiveData<List<Song>>
        get() = _singerHotSong

    val singerMv : LiveData<List<Mv>>
        get() = _singerMv

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
    fun getSimiArtist(id:String){
        Repository.getSimiSinger(id).subscribe(object : Observer<SimiSingerData> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: SimiSingerData) {
                _simiSinger.postValue(t.artists)
            }

            override fun onError(e: Throwable) {
                Log.e("Error","------ ${e.message}")
            }
            override fun onComplete() {
            }
        })
    }

    fun getSingerHotSong(id:String){
        Repository.getSingerHotSong(id).subscribe(object : Observer<SongData> {
            override fun onSubscribe(d: Disposable) {
            }
            override fun onNext(t: SongData) {
                _singerHotSong.postValue(t.songs)
            }
            override fun onError(e: Throwable) {
                Log.e("Error","------ ${e.message}")
            }
            override fun onComplete() {
            }
        })
    }

    fun getSingerMv(id:String){
        Repository.getSingerMv(id).subscribe(object : Observer<MvData> {
            override fun onSubscribe(d: Disposable) {
            }
            override fun onNext(t: MvData) {
                _singerMv.postValue(t.mvs)
                Log.d("Mv","${t.mvs}")
            }
            override fun onError(e: Throwable) {
                Log.e("Error","------ ${e.message}")
            }
            override fun onComplete(){
            }
        })
    }

}