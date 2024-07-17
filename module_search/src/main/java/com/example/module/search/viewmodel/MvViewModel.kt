package com.example.module.search.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.module.search.bean.MvData
import com.example.module.search.repositury.NetRepository
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MvViewModel:ViewModel() {
    private val data= MutableLiveData<MvData>()
    val mvData:MutableLiveData<MvData>
        get()=data
    fun getMvData(keywords:String,type:Int,limit:Int){
        NetRepository.getSearchMvData(keywords, type,limit).subscribe(object :Observer<MvData>{
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d("TAG", "${e.message}" )
            }

            override fun onComplete() {
            }

            override fun onNext(t: MvData) {
                Log.d("Mv","${t}")
                data.postValue(t)
            }

        })
    }
}