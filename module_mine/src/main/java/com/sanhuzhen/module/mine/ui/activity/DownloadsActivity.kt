package com.sanhuzhen.module.mine.ui.activity

import android.os.Bundle
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
import com.sanhuzhen.module.mine.databinding.ActivityDownloadsBinding
import com.therouter.TheRouter

class DownloadsActivity : BaseActivity<ActivityDownloadsBinding>(){
    val mRvAdapter:DownloadsRvAdapter by lazy { DownloadsRvAdapter() }
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
            val SongList= mutableListOf<String>()
            for (i in mRvAdapter.currentList){
                SongList.add(i.id)
            }
            TheRouter.build("/musicplayer/musicplayerActivity").withObject("SongList", SongList)
                .navigation()
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
            mRvAdapter.submitList(contacts)
    }
    fun getback(){
        mBinding.ivBack.setOnClickListener {
            finish()
        }
    }
}