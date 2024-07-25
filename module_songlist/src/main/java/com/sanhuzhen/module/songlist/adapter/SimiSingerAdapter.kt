package com.sanhuzhen.module.songlist.adapter

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
import com.sanhuzhen.module.songlist.R
import com.sanhuzhen.module.songlist.bean.sArtist
import com.therouter.TheRouter

class SimiSingerAdapter :
    ListAdapter<sArtist, SimiSingerAdapter.mViewHolder>(object : DiffUtil.ItemCallback<sArtist>() {
        override fun areItemsTheSame(oldItem: sArtist, newItem: sArtist): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: sArtist, newItem: sArtist): Boolean {
            return oldItem.id == newItem.id
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimiSingerAdapter.mViewHolder {
        return mViewHolder(View.inflate(parent.context, R.layout.homepageslideplaylist_rv_item, null))
    }

    override fun onBindViewHolder(holder: SimiSingerAdapter.mViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val singerImage: ImageView = itemView.findViewById(R.id.playlist_iv)
        private val singerName: TextView = itemView.findViewById(R.id.playlist_tv)

        init {
            setOnClickListener()
        }

        fun setOnClickListener() {
            itemView.setOnClickListener {
                TheRouter.build("/songlist/singer")
                    .withString("SingerId", getItem(adapterPosition).id.toString())
                    .navigation()
            }
        }
        fun bind(item: sArtist) {
            Glide.with(itemView.context).load(item.picUrl)
                .transform(CenterCrop(), RoundedCorners(360)).into(singerImage)
            singerName.text = item.name
        }
    }
}