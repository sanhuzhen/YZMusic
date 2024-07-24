package com.sanhuzhen.module.recommend.adapter

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
import com.sanhuzhen.module.home.R
import com.sanhuzhen.module.recommend.bean.SongData
import com.therouter.TheRouter

class SongListAdapter :
    ListAdapter<SongData, SongListAdapter.mViewHolder>(object : DiffUtil.ItemCallback<SongData>() {
        override fun areItemsTheSame(oldItem: SongData, newItem: SongData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SongData, newItem: SongData): Boolean {
            return oldItem.id == newItem.id
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListAdapter.mViewHolder {
        return mViewHolder(View.inflate(parent.context, R.layout.layout_songlist, null))
    }

    override fun onBindViewHolder(holder: SongListAdapter.mViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val songIv: ImageView = itemView.findViewById(R.id.song_image)
        private val songNameTv: TextView = itemView.findViewById(R.id.song_name)
        private val songSingerTv: TextView = itemView.findViewById(R.id.song_singer)

        init {
            setOnClickListener()
        }

        fun setOnClickListener() {
            itemView.setOnClickListener {
                TheRouter.build("/musicplayer/musicplayerActivity")
                    .withObject("SongList", arrayListOf(getItem(adapterPosition).id.toString()))
                    .navigation()
            }
        }
        fun bind(item: SongData) {
            Glide.with(itemView.context).load(item.album.blurPicUrl)
                .transform(CenterCrop(), RoundedCorners(10)).into(songIv)
            for (i in item.artists){
                songSingerTv.text = i.name
            }
            songNameTv.text = item.name
        }
    }
}