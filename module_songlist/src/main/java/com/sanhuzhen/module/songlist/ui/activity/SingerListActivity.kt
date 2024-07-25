package com.sanhuzhen.module.songlist.ui.activity

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.songlist.adapter.SingerAdapter
import com.sanhuzhen.module.songlist.databinding.ActivitySingerBinding
import com.sanhuzhen.module.songlist.ui.fragment.MvFragment
import com.sanhuzhen.module.songlist.ui.fragment.EncyclopediaFragment
import com.sanhuzhen.module.songlist.ui.fragment.SongFragment
import com.sanhuzhen.module.songlist.viewmodel.SingerViewModel
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

    private val singerViewModel by lazy {
        ViewModelProvider(this)[SingerViewModel::class.java]
    }

    override fun afterCreate() {
        initVp()
        someNet()
        setSupportActionBar(mBinding.singerToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        mBinding.singerToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun getViewBinding(): ActivitySingerBinding {
        return ActivitySingerBinding.inflate(layoutInflater)
    }

    //固定Vp2
    private fun initVp() {
        vpAdapter.apply {
            addFragment(EncyclopediaFragment())
            addFragment(SongFragment())
            addFragment(MvFragment())
        }
        mBinding.singerViewpager.adapter = vpAdapter
        mBinding.singerViewpager.isUserInputEnabled = false
        TabLayoutMediator(mBinding.singerTabs, mBinding.singerViewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "百科"
                }

                1 -> {
                    tab.text = "歌曲"
                }

                2 -> {
                    tab.text = "MV"
                }
            }
        }.attach()
        mBinding.singerTabs.getTabAt(1)?.select()
    }

    private fun someNet() {
        Log.d("SingerListActivity", "SingerId:$SingerId")
        singerViewModel.apply {
            getSingerList(SingerId!!)
            getSimiArtist(SingerId!!)
            getSingerDes(SingerId!!)
            getSingerHotSong(SingerId!!)
            getSingerMv(SingerId!!)
            singerList.observe(this@SingerListActivity) {
                Glide.with(this@SingerListActivity).load(it.cover).into(mBinding.singerImage)
                mBinding.singerCollapsingToolbar.title = it.name
            }
        }
    }
}