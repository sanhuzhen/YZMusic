package com.sanhuzhen.module.mine.ui.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
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
import com.sanhuzhen.module.mine.bean.WeekData
import com.sanhuzhen.module.mine.databinding.ActivityHistoryBinding
import com.sanhuzhen.module.mine.viewmodel.BaseViewModel
import com.therouter.TheRouter

class HistoryActivity :BaseActivity<ActivityHistoryBinding>(){

    private var MyId :Long?=null
    val mViewModel: BaseViewModel by lazy { ViewModelProvider(this)[BaseViewModel::class.java] }
    val rvAdapter : HistoryRvAdapter by lazy { HistoryRvAdapter() }
    private val SongList:MutableList<WeekData> = mutableListOf()
    private val SongLists = arrayListOf<String>()
    override fun afterCreate() {
        mBinding.pbLoading.visibility= View.VISIBLE
        mBinding.pbLoading.progress=0
        getsp()
        getitem()
        getback()
        playall()

    }

    override fun getViewBinding(): ActivityHistoryBinding {
        return ActivityHistoryBinding.inflate(layoutInflater)
    }
    fun playall(){
        mBinding.ivAll.setOnClickListener {
            TheRouter.build("/musicplayer/musicplayerActivity").withObject("SongList", SongLists)
                .navigation()
        }
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
            SongList.addAll(it.weekData)
            rvAdapter.submitList(it.weekData)
            mBinding.pbLoading.progress=100
            mBinding.pbLoading.visibility= View.GONE
            for (i in SongList){
                SongLists.add(i.song.id.toString())
            }
        }

    }
    fun getback(){
        mBinding.ivBack.setOnClickListener {
            finish()
        }
    }
    override fun onResume() {
        super.onResume()
        for (i in SongList){
            SongLists.add(i.song.id.toString())
        }
    }
}