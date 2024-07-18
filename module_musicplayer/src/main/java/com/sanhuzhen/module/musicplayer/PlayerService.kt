package com.sanhuzhen.module.musicplayer

import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService

class PlayerService : MediaSessionService() {
    private var exoPlayer: ExoPlayer? = null
    private var mediaSession: MediaSession? = null
    override fun onCreate() {
        super.onCreate()
        //创建ExoPlayer
        exoPlayer = ExoPlayer.Builder(this).build()
        // 基于已创建的ExoPlayer创建MediaSession
        exoPlayer?.let { mediaSession = MediaSession.Builder(this, it).build() }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 释放相关实例
        exoPlayer?.stop()
        exoPlayer?.release()
        exoPlayer = null
        mediaSession?.release()
        mediaSession = null
    }
    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

}