package com.sanhuzhen.module.recommend

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.module.recommend.bean.HomeData
import com.sanhuzhen.module.recommend.network.RetrofitRequest
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class RecommendViewModel: ViewModel() {
    private val _homeData = MutableLiveData<HomeData>()

    init {
        getHomeData()
    }
    val homeData: MutableLiveData<HomeData>
        get() = _homeData

    fun getHomeData(){
        RetrofitRequest.getHomeData().subscribe(object : Observer<HomeData>{
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d("Error"," -----------  ${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: HomeData) {
                _homeData.postValue(t)
                Log.d("RecommendViewModel","---------  ${t}")
            }

        })
    }
}