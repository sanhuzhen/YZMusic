package com.sanhuzhen.module.mvplayer.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.module.mvplayer.bean.Data
import com.sanhuzhen.module.mvplayer.bean.MvDetailData
import com.sanhuzhen.module.mvplayer.bean.MvUrlData
import com.sanhuzhen.module.mvplayer.bean.SimiMvData
import com.sanhuzhen.module.mvplayer.bean.mData
import com.sanhuzhen.module.mvplayer.repository.NetWork
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MvPlayerViewModel : ViewModel() {
    private val _mvUrl = MutableLiveData<Data>()
    private val _mvDetail = MutableLiveData<mData>()
    private val _simiMv = MutableLiveData<SimiMvData>()
    val mvUrl: MutableLiveData<Data>
        get() = _mvUrl
    val mvDetail: MutableLiveData<mData>
        get() = _mvDetail
    val simiMv: MutableLiveData<SimiMvData>
        get() = _simiMv
    fun getMvUrl(id: String){
        NetWork.getMvUrl(id).subscribe(object : Observer<MvUrlData> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.e("Error","------ ${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: MvUrlData) {
                _mvUrl.postValue(t.data)
               Log.d("MvPlayerViewModel","-------- $t")
            }
        })
    }
    fun getMvDetail(mvid: String){
        NetWork.getMvDetail(mvid).subscribe(object : Observer<MvDetailData> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: MvDetailData) {
                _mvDetail.postValue(t.data)
                Log.d("MvPlayerViewModel","-------- $t")
            }

            override fun onError(e: Throwable) {
                Log.e("Error","------ ${e.message}")
            }

            override fun onComplete() {

            }
        })
    }
    fun getSimiMv(mvid: String){
        NetWork.getSimiMv(mvid).subscribe(object : Observer<SimiMvData> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: SimiMvData) {
                _simiMv.postValue(t)
                Log.d("MvPlayerViewModel","-------- $t")
            }

            override fun onError(e: Throwable) {
                Log.e("Error","------ ${e.message}")
            }

            override fun onComplete() {

            }
        })
    }
}