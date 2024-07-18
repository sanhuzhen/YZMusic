package com.sanhuzhen.module.mvplayer.adapter

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
import com.sanhuzhen.module.mvplayer.R
import com.sanhuzhen.module.mvplayer.bean.Artist

class ArtistRvAdapter :
    ListAdapter<Artist, ArtistRvAdapter.mViewHolder>(object : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.name == newItem.name && oldItem.img1v1Url == newItem.img1v1Url
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.id == newItem.id
        }

    }) {
    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistIv: ImageView = itemView.findViewById(R.id.mv_artist_iv)
        private val artistTv: TextView = itemView.findViewById(R.id.mv_artist_name)
        fun bind(artist: Artist) {
            artistTv.text = artist.name
            Glide.with(itemView.context).load(artist.img1v1Url)
                .transform(CenterCrop(), RoundedCorners(90)).into(artistIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(View.inflate(parent.context, R.layout.item_artist_rv, null))
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}