package com.sanhuzhen.module.mvplayer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanhuzhen.module.mvplayer.bean.CommentData
import com.sanhuzhen.module.mvplayer.bean.MvDetailData
import com.sanhuzhen.module.mvplayer.bean.OtherData
import com.sanhuzhen.module.mvplayer.bean.SingerData
import com.sanhuzhen.module.mvplayer.repository.NetRepository
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class BaseViewModel:ViewModel() {
    private val mvUrl= MutableLiveData<MvDetailData>()
    val mMvUrl:LiveData<MvDetailData>
        get() = mvUrl
    private val singer=MutableLiveData<SingerData>()
    val mSinger:LiveData<SingerData>
        get() = singer
    private val comment=MutableLiveData<CommentData>()
    val mComment:LiveData<CommentData>
        get() = comment
    private val mvDetail=MutableLiveData<OtherData>()
    val mMvDetail:LiveData<OtherData>
        get() = mvDetail
    fun getMvUrl(id:String){
        NetRepository.getMvUrl(id).subscribe(object : Observer<MvDetailData> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d("MvUrlError","${e.message}")
            }

            override fun onComplete() {
            }

            override fun onNext(t: MvDetailData) {
                Log.d("MvUrl","${t}")
                mvUrl.postValue(t)
            }
        })
    }
    fun getSinger(mvid:String){
        NetRepository.getMVSinger(mvid).subscribe(object : Observer<SingerData> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: SingerData) {
                Log.d("SingerData","${t}")
                singer.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("SingerError","${e.message}")
            }

            override fun onComplete() {

            }

        })
    }
    fun getComment(id:String){
        NetRepository.getComment(id,).subscribe(object : Observer<CommentData> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: CommentData) {
                Log.d("CommentData","${t}")
                comment.postValue(t)
            }

            override fun onError(e: Throwable) {
                Log.d("Comment","${e.message}")
            }

            override fun onComplete() {

            }
        })
    }
    fun getMvDetail(mvid:String){
        NetRepository.getOther(mvid).subscribe(object : Observer<OtherData> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d("DetailError","${e.message}")
            }

            override fun onComplete() {

            }


            override fun onNext(t: OtherData) {
                Log.d("MvDetail","${t}")
                mvDetail.postValue(t)
            }

        })
    }
}