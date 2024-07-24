package com.sanhuzhen.module.songlist.ui.activity

import android.util.Log
import com.google.android.material.tabs.TabLayoutMediator
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.songlist.adapter.SingerAdapter
import com.sanhuzhen.module.songlist.databinding.ActivitySingerBinding
import com.sanhuzhen.module.songlist.ui.fragment.AlbumFragment
import com.sanhuzhen.module.songlist.ui.fragment.EncyclopediaFragment
import com.sanhuzhen.module.songlist.ui.fragment.SongFragment
import com.therouter.router.Autowired
import com.therouter.router.Route

/**
 * @author: sanhuzhen
 * @date: 2024/7/24
 * @description: 歌手列表
 */
@Route(path = "/songlist/singer")
class SingerListActivity : BaseActivity<ActivitySingerBinding>() {

    @Autowired
    @JvmField
    var SingerId: String? = null

    private val vpAdapter by lazy {
        SingerAdapter(this)
    }
    override fun afterCreate() {
        Log.d("SingerListActivity", "singerId:$SingerId")
        initVp()
    }

    override fun getViewBinding(): ActivitySingerBinding {
        return ActivitySingerBinding.inflate(layoutInflater)
    }

    //固定Vp2
    private fun initVp() {
        vpAdapter.apply {
            addFragment(EncyclopediaFragment())
            addFragment(SongFragment())
            addFragment(AlbumFragment())
        }
        mBinding.singerViewpager.adapter = vpAdapter
        TabLayoutMediator(mBinding.singerTabs, mBinding.singerViewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "百科"
                }
                1 -> {
                    tab.text = "歌曲"
                }
                2 -> {
                    tab.text = "专辑"
                }
            }
        }.attach()
        mBinding.singerTabs.getTabAt(1)?.select()
    }
}