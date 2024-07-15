package com.example.module.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.module.login.bean.MyData
import com.example.module.login.repository.NetRepositoryPhone
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MyViewModel:ViewModel() {
    private val num=MutableLiveData<MyData>()


    val _num:MutableLiveData<MyData>
        get() = num

    fun getNum(email:String,password:String){
        NetRepositoryPhone.getSearchDataEmail(email,password).subscribe(object : Observer<MyData>{
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("Error", "${e.message} ")
            }

            override fun onComplete() {

            }

            override fun onNext(t: MyData) {
                num.postValue(t)
                Log.d("onNext", "${t}")

            }

        })

    }
    fun getNumber(phone:String,password:String){
        NetRepositoryPhone.getSearchDataPhone(phone,password).subscribe(object : Observer<MyData>{
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("Error", "${e.message} ")
            }

            override fun onComplete() {
            }

            override fun onNext(t: MyData) {
                num.postValue(t)
                Log.d("onNext", "${t}")
            }

        })
    }
    fun getSend(phone:String){
        NetRepositoryPhone.getSearchDataSend(phone).subscribe(object : Observer<MyData>{
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("Error", "${e.message} ")
            }

            override fun onComplete() {
            }

            override fun onNext(t: MyData) {
                num.postValue(t)
                Log.d("onNext", "${t}")
            }
        })
    }
    fun getVerify(phone:String,captcha:String){
        NetRepositoryPhone.getSearchDataVerify(phone,captcha).subscribe(object : Observer<MyData>{
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d("Error", "${e.message} ")
            }

            override fun onComplete() {
            }

            override fun onNext(t: MyData) {
                num.postValue(t)
            }
        })
    }
}