package com.example.module.search.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedVIewModel: ViewModel(){
    val someData = MutableLiveData<String>()

    // 用于设置数据的方法
    fun setData(data: String) {
        someData.value = data
    }
}