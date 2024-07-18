package com.sanhuzhen.module.musicplayer

import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.sanhuzhen.lib.base.BaseActivity
import com.sanhuzhen.module.musicplayer.databinding.ActivityPlayerBinding

class PlayerActivity : BaseActivity<ActivityPlayerBinding>() {
    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView
    override fun afterCreate() {
        playerView = findViewById(R.id.player_view)
        player = ExoPlayer.Builder(this).build()
        playerView.player = player

        val mediaItem = MediaItem.fromUri("http://vodkgeyttp8.vod.126.net/cloudmusic/3793/core/de43/667c55c652dbc078566ca5b4447a6e7b.mp4?wsSecret=bfe349998cdde49b3a5c883b999d2cee&wsTime=1721266003")
        player.setMediaItem(mediaItem)
        player.prepare()
        player.playWhenReady = true
    }
    override fun onStart() {
        super.onStart()
        if (player != null) {
            player.playWhenReady = true
        }
    }

    override fun onStop() {
        super.onStop()
        if (player != null) {
            player.playWhenReady = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }

    override fun getViewBinding(): ActivityPlayerBinding {
        return ActivityPlayerBinding.inflate(layoutInflater)
    }


}