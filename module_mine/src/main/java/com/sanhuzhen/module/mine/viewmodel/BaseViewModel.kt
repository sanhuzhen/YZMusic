package com.sanhuzhen.module.mine.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.module.mine.bean.BaseData
import com.sanhuzhen.module.mine.bean.CloudData
import com.sanhuzhen.module.mine.bean.FavouriteData
import com.sanhuzhen.module.mine.bean.FocusData
import com.sanhuzhen.module.mine.bean.HistoryData
import com.sanhuzhen.module.mine.bean.PLData
import com.sanhuzhen.module.mine.repository.NetRepository
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class BaseViewModel:ViewModel() {
    private val base=MutableLiveData<BaseData>()
    val mBase:MutableLiveData<BaseData>
        get() = base
    private val history=MutableLiveData<HistoryData>()
    val mHistory:MutableLiveData<HistoryData>
        get() = history
    private val pL=MutableLiveData<PLData>()
    val mPL:MutableLiveData<PLData>
        get() = pL
    private val favourite=MutableLiveData<FavouriteData>()
    val mFavourite:MutableLiveData<FavouriteData>
        get() = favourite
    private val cloud=MutableLiveData<CloudData>()
    val mCloud:MutableLiveData<CloudData>
        get() = cloud
    private val focus=MutableLiveData<FocusData>()
    val mFocus:MutableLiveData<FocusData>
        get() = focus
    fun getBase(uid:Long){
        NetRepository.getSearchData(uid).subscribe(object : Observer<BaseData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("BaseError","${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: BaseData) {
                base.postValue(t)
            }

        })
    }
    fun getHistory(uid: Long,type:Int){
        NetRepository.getRecordData(uid,type).subscribe(object : Observer<HistoryData> {
            override fun onSubscribe(d: Disposable) {

            }



            override fun onError(e: Throwable) {
                Log.d("HistoryError","${e.message}")
            }

            override fun onComplete() {

            }
            override fun onNext(t: HistoryData) {
                history.postValue(t)
                Log.d("BaseHistory", "${t}")
            }

        })
    }
    fun getPL(uid:Long){
        NetRepository.getPLData(uid).subscribe(object : Observer<PLData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("PLError","${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: PLData) {
                pL.postValue(t)
                Log.d("BasePL", "${t}")
            }

        })
    }
    fun getFavourite(id:Long){
        NetRepository.getFavouriteData(id).subscribe(object : Observer<FavouriteData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("FavouriteError","${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: FavouriteData) {
                Log.d("BaseFavourite", "${t}")
                favourite.postValue(t)
            }
        })
    }
    fun getCloud(limit:Int){
        NetRepository.getCloudData(limit).subscribe(object : Observer<CloudData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("CloudError","${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: CloudData) {
                Log.d("BaseCloud", "${t}")
               cloud.postValue(t)
            }
        })
    }
    fun getFocus(uid:Long){
        NetRepository.getFollowsData(uid).subscribe(object : Observer<FocusData> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("FocusError","${e.message}")
            }

            override fun onComplete() {

            }

            override fun onNext(t: FocusData) {
                Log.d("BaseFocus", "${t}")
                focus.postValue(t)
            }
        })
    }
}