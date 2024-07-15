package com.sanhuzhen.yzmusic.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.yzmusic.bean.Data
import com.sanhuzhen.yzmusic.bean.HomeData
import com.sanhuzhen.yzmusic.network.RetrofitRequest
import io.reactivex.rxjava3.core.Observer

class RecommendViewModel : ViewModel() {
    private val _blocks = MutableLiveData<HomeData>()

    val blocks : MutableLiveData<HomeData>
        get() = _blocks

    fun getBlocks(){
        RetrofitRequest.getBlocks().subscribe(object : Observer<HomeData>{
            override fun onSubscribe(d: io.reactivex.rxjava3.disposables.Disposable) {
            }

            override fun onNext(t: HomeData) {
                _blocks.postValue(t)
                Log.d("RecommendViewModel","-----------  $t")
            }

            override fun onError(e: Throwable) {
                Log.d("Error","---------  ${e.message}")
            }

            override fun onComplete() {
            }
        })
    }
}