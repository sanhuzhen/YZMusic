package com.example.module.search.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.module.search.R
import com.example.module.search.bean.Song
import com.sanhuzhen.lib.base.helper.SongDataHelper

class MusicRvAdapter:ListAdapter<Song,MusicRvAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.id==newItem.id&&oldItem.name==newItem.name&&oldItem.album.id==newItem.album.id&&oldItem.artists==newItem.artists
    }

    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.id==newItem.id&&oldItem.name==newItem.name&&oldItem.album.id==newItem.album.id&&oldItem.artists==newItem.artists
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicRvAdapter.ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.music_rv,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MusicRvAdapter.ViewHolder, position: Int) {
        val songData=getItem(position)
        holder.songData(songData)
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val musicName: TextView =itemView.findViewById(R.id.music_name)
        private val musicArtist: TextView =itemView.findViewById(R.id.music_singer)
        private val musicAlbum: TextView =itemView.findViewById(R.id.music_album)
        private val musicElse:ImageView=itemView.findViewById(R.id.music_else)
        fun songData(musicData: Song){
            musicName.text=musicData.name
            musicAlbum.text=musicData.album.name
            musicAlbum.maxLines=1
            musicArtist.text=musicData.artists[0].name
            musicElse.setOnClickListener{
                val popup = PopupMenu(itemView.context, musicElse)
                popup.menuInflater.inflate(R.menu.sample, popup.menu)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.share -> {
                            val shareIntent = Intent().apply {
                                val title = "我发现了一首比较不错的音乐，分享给你\n${musicData.name} -- ${musicData.artists[0].name}"
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, title)
                                type = "text/plain"
                            }
                            // 启动分享活动
                            startActivity(itemView.context,
                                Intent.createChooser(shareIntent,"选择分享应用"),null)
                            true
                        }
                        R.id.downloads -> {
                            val dbHelper=SongDataHelper(itemView.context,"song",1)
                            dbHelper.addBook(musicData.name,musicData.artists[0].name,
                                musicData.id.toString(), null.toString()
                            )
                            true
                        }
                        else -> false
                    }
                }
                popup.show()
            }

        }
    }
}

