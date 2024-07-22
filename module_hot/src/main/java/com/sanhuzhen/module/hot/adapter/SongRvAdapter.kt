package com.sanhuzhen.module.hot.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.module.hot.R
import com.sanhuzhen.module.hot.bean.Track

class SongRvAdapter: ListAdapter<Track, SongRvAdapter.SongHolder>(object :
    DiffUtil.ItemCallback<Track>(){
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem==newItem
    }

}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongRvAdapter.SongHolder {
        val itemView=View.inflate(parent.context, R.layout.item_songlist,null)
        return SongHolder(itemView)
    }

    override fun onBindViewHolder(holder: SongRvAdapter.SongHolder, position: Int) {
        val songListData=getItem(position)
        holder.bind(songListData)
    }
    inner class SongHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val songName: TextView=itemView.findViewById(R.id.song_name)
        private val songSinger:TextView=itemView.findViewById(R.id.song_singer)
        private val songImg:ImageView=itemView.findViewById(R.id.song_img)
        private val downloads:ImageView=itemView.findViewById(R.id.song_downloads)
        fun bind(track: Track){
            if (track.al.picUrl.isNotEmpty()){
                Glide.with(itemView.context)
                    .load(track.al.picUrl)
                    .transform(CenterCrop(), RoundedCorners(30))
                    .into(songImg)
            }else{
                songImg.setImageResource(R.drawable.ic_launcher_background)
            }
            songName.text=track.name
            songSinger.text=track.ar[0].name
            downloads.setImageResource(R.drawable.song_downloads)
        }
    }
}