package com.example.module.search.adapter

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
import com.example.module.search.R
import com.example.module.search.bean.Artist
import com.example.module.search.bean.ArtistsData

class ArtistsRvAdapter : ListAdapter <Artist, ArtistsRvAdapter.OneHolder>(object :
    DiffUtil.ItemCallback<Artist>(){
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.id == newItem.id&&oldItem.name == newItem.name&&oldItem.picUrl == newItem.picUrl
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.id == newItem.id&&oldItem.name == newItem.name&&oldItem.picUrl == newItem.picUrl
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsRvAdapter.OneHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.artists_rv,parent,false)
        return OneHolder(itemView)
    }

    override fun onBindViewHolder(holder:OneHolder, position: Int) {
        val artistData=getItem(position)
        holder.artistData(artistData)

    }
    inner class OneHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ArtistImg:ImageView=itemView.findViewById(R.id.artist_image)
        private val ArtistName:TextView=itemView.findViewById(R.id.artist_name)

        fun artistData(artistData: Artist){
            ArtistName.text=artistData.name
            if(artistData.picUrl.isNotEmpty()){
                Glide.with(itemView.context).load(artistData.picUrl)
                    .transform(CenterCrop(), RoundedCorners(60)).into(ArtistImg)
            }
            else{
                ArtistImg.setImageResource(R.drawable.ic_launcher_foreground)
            }

        }
    }
}