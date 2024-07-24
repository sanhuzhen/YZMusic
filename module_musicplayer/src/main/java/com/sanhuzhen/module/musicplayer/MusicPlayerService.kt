package com.sanhuzhen.module.musicplayer

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.sanhuzhen.module.musicplayer.bean.Song
import com.sanhuzhen.module.musicplayer.viewmodel.PlayViewModel
import java.util.ArrayList

/**
 * @author: sanhuzhen
 * @data: 2024/7/21
 * @description: 使用Service实现音乐播放
 */
class MusicPlayerService : Service() {
    private lateinit var player: Player
    val musicBinder = MusicBinder()


    //得到音乐详细信息，为给MainActivity中的音乐播放器绑定
    private var musicId = arrayListOf<String>()
    private var musicUrlList = arrayListOf<String>()
    private var musicDetail = arrayListOf<Song>()
    private var currentPosition: Int = 0 //当前音乐播放播放位置


    //实现通信
    inner class MusicBinder : Binder() {
        fun startPlay(musicUrl: String) {
            val mediaItem = MediaItem.fromUri(musicUrl)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.playWhenReady = true

        }

        //播放音乐
        fun playMusic() {
            if (!player.isPlaying) {
                player.playWhenReady = true
            }
        }

        //停止音乐
        fun stopMusic() {
            if (player.isPlaying) {
                player.playWhenReady = false
            }
        }

        //跳转音乐指定位置
        fun seekTo(position: Long) {
            player.seekTo(position)
        }

        //获取当前播放位置
        fun getCurrentPosition(): Long {
            return player.currentPosition
        }

        //获取总时长
        fun getDuration(): Long {
            return player.duration
        }

        //播放下一首
        fun nextMusic() {
            player.stop()
            if (currentPosition >= 0 && currentPosition < musicUrlList.size - 1) {
                currentPosition += 1
                musicBinder.startPlay(musicUrlList[currentPosition])
            } else {
                musicBinder.startPlay(musicUrlList[0])
                currentPosition = 0
            }
        }

        //播放上一首
        fun preMusic() {
            player.stop()
            if (currentPosition > 0 && currentPosition <= musicUrlList.size - 1) {
                currentPosition -= 1
                musicBinder.startPlay(musicUrlList[currentPosition])
            } else {
                musicBinder.startPlay(musicUrlList[musicUrlList.size - 1])
                currentPosition = musicUrlList.size - 1
            }
        }

        fun changeMusicInfo(position: Int) {
            player.stop()
            currentPosition = position
            musicBinder.startPlay(musicUrlList[position])
        }

        //获取音乐URL
        fun setMusicUrl(MusicUrl: ArrayList<String>) {
            musicUrlList = MusicUrl
        }

        fun returnMusicUrl(): ArrayList<String> {
            return musicUrlList
        }

        //获取当前播放状态
        fun getPlayWhenReady(): Boolean {
            return player.isPlaying
        }

        //获取音乐信息
        fun setMusicInfo(MusicDetail: ArrayList<Song>) {
            musicDetail = MusicDetail
        }

        //返回当前音乐信息
        fun returnMusic(): ArrayList<Song> {
            return musicDetail
        }

        //获取音乐position，后面获取音乐的详细信息
        fun getMusicPosition(): Int {
            return currentPosition
        }

        fun setMusicId(MusicId: ArrayList<String>) {
            musicId = MusicId
        }
        fun returnMusicId(): ArrayList<String> {
            return musicId
        }

        //清空数据
        fun clear() {
            if (musicId.isNotEmpty()&&player.isPlaying){
                player.stop()
                player.clearMediaItems()
                currentPosition = 0
            }else if (musicId.isNotEmpty()&&!player.isPlaying){
                player.clearMediaItems()
                currentPosition = 0
            }
        }

    }

    override fun onCreate() {
        super.onCreate()
        createNoticeChannel()
        /**
         * 创建播放器
         */
        player = ExoPlayer.Builder(this).build().apply {
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    when (playbackState) {
                        Player.STATE_ENDED -> {
                            //播放结束，自动播放下一首音乐
                            musicBinder.nextMusic()
                            //在音乐播放结束时发送广播
                            val intent = Intent("com.sanhuzhen.module.musicplayer.MUSIC_COMPLETE")
                            intent.setPackage(packageName)
                            sendBroadcast(intent)
                        }
                    }
                }
            })
        }

    }


    override fun onBind(intent: Intent?): IBinder {
        return musicBinder
    }

    //创建通知栏
    private fun createNoticeChannel() {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = android.app.NotificationChannel(
                "my_service",
                "前台Service通知",
                NotificationManager.IMPORTANCE_LOW
            )
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, MusicPlayerActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("YZMusic")
            .setContentText("音乐正在后台为你服务")
            .setSmallIcon(R.drawable.music)
            .setContentIntent(pi)
            .setAutoCancel(true)
            .build()
        startForeground(1, notification)
    }
}