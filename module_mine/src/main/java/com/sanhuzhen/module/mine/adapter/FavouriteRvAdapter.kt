package com.sanhuzhen.module.mine.adapter

import android.view.LayoutInflater
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
import com.sanhuzhen.module.mine.R
import com.sanhuzhen.module.mine.bean.PlaylistX
import com.sanhuzhen.module.mine.bean.Track

class FavouriteRvAdapter:ListAdapter<Track,FavouriteRvAdapter.FavHolder>(object :
    DiffUtil.ItemCallback<Track>(){
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }
}) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_favourite,parent,false)
        return FavHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavHolder, position: Int) {
        val favouriteData=getItem(position)
        holder.favouriteData(favouriteData)
    }
    inner class FavHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val FavImg: ImageView =itemView.findViewById(R.id.iv_song_img)
        val FavSong: TextView =itemView.findViewById(R.id.tv_song_name)
        val FavSinger: TextView =itemView.findViewById(R.id.tv_singer_name)
        fun favouriteData(favouriteData: Track){
            if (favouriteData.al.picUrl.isNotEmpty()){
                Glide.with(itemView.context).load(favouriteData.al.picUrl)
                    .transform(CenterCrop(), RoundedCorners(60)).into(FavImg)
            }
            else{
                FavImg.setImageResource(R.drawable.ic_launcher_foreground)
            }
            FavSong.text=favouriteData.name
            FavSinger.text=favouriteData.ar[0].name

        }
    }
}