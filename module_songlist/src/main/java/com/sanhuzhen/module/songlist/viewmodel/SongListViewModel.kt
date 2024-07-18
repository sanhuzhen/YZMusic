package com.sanhuzhen.module.songlist.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.module.songlist.bean.Song
import com.sanhuzhen.module.songlist.network.Repository
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class SongListViewModel: ViewModel() {
    private val _playList = MutableLiveData<Song>()

    val playList : MutableLiveData<Song>
        get() = _playList

    fun getPlayList(id:String,limit:Int,offset:Int){
        Repository.getPlayList(id,limit,offset).subscribe(object : Observer<Song>{
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d("Error","------ ${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: Song) {
                _playList.postValue(t)
                Log.d("SongListViewModel","-------- $t")
            }

        })
    }
}