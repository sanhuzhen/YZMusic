package com.sanhuzhen.module.songlist.activity

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.songlist.databinding.ActivitySonglistBinding
import com.sanhuzhen.module.songlist.viewmodel.SongListViewModel
import com.therouter.router.Autowired
import com.therouter.router.Route

/**
 * @author: sanhuzhen
 * @date: 2024/7/17
 * @description:歌单详细信息
 */
@Route(path = "/songlist/songListActivity")
class SongListActivity : BaseActivity<ActivitySonglistBinding>() {

    override fun afterCreate() {

    }

    override fun getViewBinding(): ActivitySonglistBinding {
        return ActivitySonglistBinding.inflate(layoutInflater)
    }

}