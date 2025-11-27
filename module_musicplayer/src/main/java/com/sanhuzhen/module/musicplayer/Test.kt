package com.sanhuzhen.module.musicplayer

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * description:
 * author: sanhuzhen
 * date: 2025/4/19 21:10
 */
fun main() {
    Observable.create<Int> {
        it.onNext(1)
    }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Int) {

            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }

        })
}