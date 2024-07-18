package com.sanhuzhen.module.songlist.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.module.songlist.bean.Playlist
import com.sanhuzhen.module.songlist.bean.SongListData
import com.sanhuzhen.module.songlist.network.Repository
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class SongListViewModel: ViewModel() {
    private val _playList = MutableLiveData<Playlist>()

    val playList : MutableLiveData<Playlist>
        get() = _playList

    fun getPlayList(id:String){
        Repository.getPlayList(id).subscribe(object : Observer<SongListData>{
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d("Error","------ ${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: SongListData) {
                _playList.postValue(t.playlist)
                Log.d("SongListViewModel","-------- $t")
            }

        })
    }
}