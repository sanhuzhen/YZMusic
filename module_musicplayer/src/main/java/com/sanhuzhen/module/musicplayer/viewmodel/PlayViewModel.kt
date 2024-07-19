package com.sanhuzhen.module.musicplayer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanhuzhen.module.musicplayer.bean.Data
import com.sanhuzhen.module.musicplayer.bean.MusicUrlData
import com.sanhuzhen.module.musicplayer.bean.MusicUsdData
import com.sanhuzhen.module.musicplayer.bean.Song
import com.sanhuzhen.module.musicplayer.bean.SongDetailData
import com.sanhuzhen.module.musicplayer.repository.NetWork
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayViewModel : ViewModel() {
    private val _musicUrl = MutableLiveData<Data>()
    private val _checkMusic = MutableLiveData<MusicUsdData>()
    private val _songDetail = MutableLiveData<Song>()

//    private val _mutableStateFlow = MutableStateFlow<MusicUsdData?>(null)
//    val mutableStateFlow get() = _mutableStateFlow.asStateFlow()

    val musicUrl: MutableLiveData<Data>
        get() = _musicUrl
    val checkMusic: MutableLiveData<MusicUsdData>
        get() = _checkMusic
    val songDetail: MutableLiveData<Song>
        get() = _songDetail

    fun getMusicUrl(id: String) {
        NetWork.getMusicUrl(id).subscribe(object : Observer<MusicUrlData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("Error", "---------  ${e.message}")
            }

            override fun onComplete() {

            }
            override fun onNext(t: MusicUrlData) {
                _musicUrl.postValue(t.data[0])
                Log.d("Success", "---------  ${t.data[0].url}")
            }
        })
    }
//    fun checkMusic(id: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response=NetWork.apiService.checkMusic(id)
//                _mutableStateFlow.emit(response)
//            } catch (e: Exception) {
//                Log.d("Error", "---------  ${e.message}")
//            }
//        }
//    }

    fun checkMusic(id: String) {
        NetWork.checkMusic(id).subscribe(object : Observer<MusicUsdData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("Error", "---------  ${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: MusicUsdData) {
                _checkMusic.postValue(t)
                Log.d("Success", "---------  ${t}")
            }
        })
    }
    fun getSongDetail(ids:String){
        NetWork.getSongDetail(ids).subscribe(object : Observer<SongDetailData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("Error", "---------  ${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: SongDetailData) {
                _songDetail.postValue(t.songs[0])
                Log.d("Success", "---------  ${t}")
            }
        })
    }
}