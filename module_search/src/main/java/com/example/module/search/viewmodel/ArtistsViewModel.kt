package com.example.module.search.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.module.search.bean.ArtistsData
import com.example.module.search.repositury.NetRepository
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class ArtistsViewModel: ViewModel() {
    private val data=MutableLiveData<ArtistsData>()

    val artistsData:MutableLiveData<ArtistsData>
        get() = data

    fun getArtistsData(keywords:String,type:Int){
        NetRepository.getSearchArtistsData(keywords, type).subscribe(object : Observer<ArtistsData> {
            override fun onSubscribe(d: Disposable) {
            }
            override fun onError(e: Throwable) {
                Log.d("Error", "${e.message} ")
            }
            override fun onComplete() {
            }
            override fun onNext(t: ArtistsData) {
                Log.d("onNext", "${t}")
                data.postValue(t)
            }


        })
    }
}