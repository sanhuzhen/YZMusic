package com.sanhuzhen.module.musicplayer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.module.musicplayer.bean.Data
import com.sanhuzhen.module.musicplayer.bean.MusicUrlData
import com.sanhuzhen.module.musicplayer.bean.MusicUsedData
import com.sanhuzhen.module.musicplayer.bean.Song
import com.sanhuzhen.module.musicplayer.bean.SongDetailData
import com.sanhuzhen.module.musicplayer.repository.NetWork
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class PlayViewModel:ViewModel() {
    private val _musicUrl = MutableLiveData<List<Data>>()
    private val _checkMusic = MutableLiveData<MusicUsedData>()
    private val _songDetail = MutableLiveData<Song>()

    /**
     * 通过LiveData实现数据共享，从而在Activity中接收到Fragment传来的数据，从而可以实现音乐的一些功能
     */
    private val _isPlay = MutableLiveData<Boolean>()
//    private val _changeMusic = MutableLiveData<Int>()
//    private val _currentPosition = MutableLiveData<Long>()

//    private val _mutableStateFlow = MutableStateFlow<MusicUsdData?>(null)
//    val mutableStateFlow get() = _mutableStateFlow.asStateFlow()

    val musicUrl: LiveData<List<Data>>
        get() = _musicUrl
    val checkMusic: LiveData<MusicUsedData>
        get() = _checkMusic
    val songDetail: LiveData<Song>
        get() = _songDetail

    //打碟的动画开关
    val isPlay: LiveData<Boolean>
        get() = _isPlay
//    fun isPlay(isPlay: Boolean) {
//        Log.d("PlayViewModel", "---------  $isPlay")
//        _isPlay.value = isPlay
//    }
//    //下一首和上一首
//    val changeMusic: MutableLiveData<Int>
//        get() = _changeMusic
//
//    fun changeMusic(position: Int) {
//        _changeMusic.value = position
//    }
//    //当前播放时间
//    val currentPosition: MutableLiveData<Long>
//        get() = _currentPosition
//    fun getCurrentPosition(position: Long) {
//        _currentPosition.value = position
//    }

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
                _musicUrl.postValue(t.data)
                Log.d("Success", "---------  ${t.data}")
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
        NetWork.checkMusic(id).subscribe(object : Observer<MusicUsedData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("Error", "---------  ${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: MusicUsedData) {
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