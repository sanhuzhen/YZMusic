package com.sanhuzhen.module.musicplayer

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.datasource.cache.Cache
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.SimpleCache
import androidx.media3.exoplayer.ExoPlaybackException
import androidx.media3.exoplayer.ExoPlayer
import java.util.ArrayList

@UnstableApi
class PlayerService : Service() {
    private lateinit var player: Player
    //通信
    private val binder = MusicBinder()
    //得到音乐信息，后面返回MainActivity的时候，给主播放器
    private var musicUrl: ArrayList<String>? = null
    private var currentSongIndex: Long = 0 // 当前歌曲索引


    override fun onCreate() {
        super.onCreate()
        createNotice()
        //初始化player
        player = ExoPlayer.Builder(this).build().apply {
            addListener(object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    Log.e("PlayerService", "Playback error: ${error.message}")
                }

                override fun onPlaybackStateChanged(playbackState: Int) {

                }
            })
        }
    }

    inner class MusicBinder : Binder() {
        //设置音乐列表
        fun setSongList(MusicUrl: ArrayList<String>){
            val mediaItems = MusicUrl.map { MediaItem.fromUri(it) }
            player.setMediaItems(mediaItems)
            player.prepare()
            Log.d("Service","${mediaItems}")
            player.seekTo(currentSongIndex)
            player.playWhenReady = true
        }
        //播放音乐
        fun playMusic() {
            player.playWhenReady = true
        }

        fun stopMusic() {
            player.stop()
        }
        //暂停音乐
        fun pauseMusic() {
            player.playWhenReady = false
        }
        // 播放下一首
        fun playNext() {
            stopMusic()
            if (musicUrl != null && currentSongIndex < musicUrl!!.size - 1) {
                currentSongIndex++
                player.seekTo(currentSongIndex)
                player.playWhenReady = true
            }
        }

        // 播放上一首
        fun playPrevious() {
            if (musicUrl != null && currentSongIndex > 0) {
                currentSongIndex--
                player.seekTo(currentSongIndex)
                player.playWhenReady = true
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        player.release()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(STOP_FOREGROUND_REMOVE)
        }
    }

    @SuppressLint("ForegroundServiceType")
    private fun createNotice() {
        // 创建前台服务通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "MusicServiceChannel",
                "Music Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)

            val notification = NotificationCompat.Builder(this, "MusicServiceChannel")
                .setContentTitle("Music Service")
                .setContentText("Playing Music")
                .setSmallIcon(R.drawable.music)
                .build()

            startForeground(1, notification)
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
    

}