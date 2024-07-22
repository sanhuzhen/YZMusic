package com.sanhuzhen.module.mine.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.mine.R
import com.sanhuzhen.module.mine.adapter.CloudRvAdapter
import com.sanhuzhen.module.mine.databinding.ActivityNetBinding
import com.sanhuzhen.module.mine.viewmodel.BaseViewModel

class NetActivity : BaseActivity<ActivityNetBinding>(){
    private var MyId:Long?=null
    private val mViewModel: BaseViewModel by lazy { ViewModelProvider(this)[BaseViewModel::class.java] }
    private val rvAdapter: CloudRvAdapter by lazy { CloudRvAdapter() }
    override fun afterCreate() {
        getsp()
        getItem()
        getback()
    }

    override fun getViewBinding(): ActivityNetBinding {
        return ActivityNetBinding.inflate(layoutInflater)
    }
    fun getsp(){
        val sp=this.getSharedPreferences("user", Context.MODE_PRIVATE)
        MyId=sp.getLong("id",0)
    }
    fun getback(){
        mBinding.ivBack.setOnClickListener {
            finish()
        }
    }
    fun getItem(){
        mBinding.rvCloud.apply {
            adapter=rvAdapter
            layoutManager= androidx.recyclerview.widget.LinearLayoutManager(this@NetActivity)
        }
        mViewModel.getCloud(100)
        mViewModel.mCloud.observe(this){
            rvAdapter.submitList(it.data)
        }
    }
}