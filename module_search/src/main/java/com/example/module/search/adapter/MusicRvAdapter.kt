package com.example.module.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.module.search.R
import com.example.module.search.bean.Song

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
        fun songData(musicData: Song){
            musicName.text=musicData.name
            musicAlbum.text=musicData.album.name
            musicArtist.text=musicData.artists[0].name
        }
    }
}

