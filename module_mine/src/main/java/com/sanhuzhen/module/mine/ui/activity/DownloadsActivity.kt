package com.sanhuzhen.module.mine.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.lib.base.helper.SongDataHelper
import com.sanhuzhen.module.mine.R
import com.sanhuzhen.module.mine.adapter.DownloadsRvAdapter
import com.sanhuzhen.module.mine.bean.SongData
import com.sanhuzhen.module.mine.bean.WeekData
import com.sanhuzhen.module.mine.databinding.ActivityDownloadsBinding
import com.therouter.TheRouter

class DownloadsActivity : BaseActivity<ActivityDownloadsBinding>(){
    val mRvAdapter:DownloadsRvAdapter by lazy { DownloadsRvAdapter() }
    private val SongList:MutableList<SongData> = mutableListOf()
    private val SongLists = arrayListOf<String>()
    override fun afterCreate() {
        setRv()
        getData()
        getback()
        playall()
    }

    override fun getViewBinding(): ActivityDownloadsBinding {
        return ActivityDownloadsBinding.inflate(layoutInflater)
    }
    fun playall(){
        mBinding.ivAll.setOnClickListener {
            if (SongLists.isEmpty()){
                Toast.makeText(this,"你还没有下载歌曲",Toast.LENGTH_SHORT).show()
            }
            else {
                TheRouter.build("/musicplayer/musicplayerActivity").withObject("SongList", SongList)
                .navigation()
            }
        }
    }
    fun setRv(){
        mBinding.rvDownloads.apply {
            adapter=mRvAdapter
            layoutManager=LinearLayoutManager(this@DownloadsActivity)
        }
    }
    fun getData(){
            val databaseHelper=SongDataHelper(this,"song",1)
            val db = databaseHelper.readableDatabase
            val cursor = db.query("Book", null, null, null, null, null, null)
            val contacts = mutableListOf<SongData>()

            with(cursor) {
                while (moveToNext()) {
                    val id = getString(getColumnIndexOrThrow("songId"))
                    val name = getString(getColumnIndexOrThrow("name"))
                    val singer = getString(getColumnIndexOrThrow("singer"))
                    contacts.add(SongData(id, name,singer,"0"))
                }
            }
            cursor.close()
            SongList.addAll(contacts)
            mRvAdapter.submitList(contacts)
            for (i in SongList) {
                SongLists.add(i.id)
            }
    }
    fun getback(){
        mBinding.ivBack.setOnClickListener {
            finish()
        }
    }
    override fun onResume() {
        super.onResume()
        for (i in SongList) {
            SongLists.add(i.id)
        }
    }
}