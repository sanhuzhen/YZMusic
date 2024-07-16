package com.sanhuzhen.module.musicplayer

import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.musicplayer.databinding.ActivityPlayerBinding

class PlayerActivity : BaseActivity<ActivityPlayerBinding>() {
    override fun getViewBinding(): ActivityPlayerBinding {
    return ActivityPlayerBinding.inflate(layoutInflater)
}
    override fun afterCreate() {

    }
}