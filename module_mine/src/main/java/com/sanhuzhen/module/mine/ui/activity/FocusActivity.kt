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
import com.sanhuzhen.module.mine.adapter.FocusRvAdapter
import com.sanhuzhen.module.mine.databinding.ActivityFocusBinding
import com.sanhuzhen.module.mine.viewmodel.BaseViewModel

class FocusActivity : BaseActivity<ActivityFocusBinding>(){
    private var MyId:Long?=null
    private val mViewModel: BaseViewModel by lazy { ViewModelProvider(this)[BaseViewModel::class.java] }
    private val rvAdapter: FocusRvAdapter by lazy { FocusRvAdapter() }
    override fun afterCreate() {
        getsp()
        getItem()
        getback()
    }

    override fun getViewBinding(): ActivityFocusBinding {
        return ActivityFocusBinding.inflate(layoutInflater)
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
        mBinding.rvFocus.apply {
            adapter=rvAdapter
            layoutManager= androidx.recyclerview.widget.LinearLayoutManager(this@FocusActivity)
        }
        mViewModel.getFocus(MyId!!)
        mViewModel.mFocus.observe(this){
            rvAdapter.submitList(it.follow)
        }
    }

}