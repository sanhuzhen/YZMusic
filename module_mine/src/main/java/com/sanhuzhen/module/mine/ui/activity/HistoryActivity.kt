package com.sanhuzhen.module.mine.ui.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.mine.R
import com.sanhuzhen.module.mine.adapter.HistoryRvAdapter
import com.sanhuzhen.module.mine.bean.BaseData
import com.sanhuzhen.module.mine.databinding.ActivityHistoryBinding
import com.sanhuzhen.module.mine.viewmodel.BaseViewModel

class HistoryActivity :BaseActivity<ActivityHistoryBinding>(){

    private var MyId :Long?=null
    val mViewModel: BaseViewModel by lazy { ViewModelProvider(this)[BaseViewModel::class.java] }
    val rvAdapter : HistoryRvAdapter by lazy { HistoryRvAdapter() }
    override fun afterCreate() {

        getsp()
        getitem()
        getback()

    }

    override fun getViewBinding(): ActivityHistoryBinding {
        return ActivityHistoryBinding.inflate(layoutInflater)
    }
    fun getsp(){
        val sp=this.getSharedPreferences("user", Context.MODE_PRIVATE)
        MyId=sp.getLong("id",0)
    }
    fun getitem(){
        mBinding.rvHistory.apply {
            adapter=rvAdapter
            layoutManager= LinearLayoutManager(this@HistoryActivity)
        }
        mViewModel.getHistory(MyId!!,1)
        mViewModel.mHistory.observe(this){
            rvAdapter.submitList(it.weekData)
        }

    }
    fun getback(){
        mBinding.ivBack.setOnClickListener {
            finish()
        }
    }
}