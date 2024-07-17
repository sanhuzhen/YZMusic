package com.example.module.search.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.module.search.bean.MusicData
import com.example.module.search.repositury.NetRepository
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MusicViewModel: ViewModel() {
    private val data= MutableLiveData<MusicData>()
    val musicData:MutableLiveData<MusicData>
        get()=data
    fun getMusicData(keywords: String,limit:Int){
        NetRepository.getSearchMusicData(keywords,limit).subscribe(object : Observer<MusicData> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d("Error", "${e.message} ")
            }

            override fun onComplete() {
            }

            override fun onNext(t: MusicData) {
                Log.d("Music", "${t}")
                data.postValue(t)
            }

        })
    }
}