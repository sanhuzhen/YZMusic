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
import com.sansHuzsHen.module.songlist.bean.Song
import com.therouter.TheRouter

class SimiSongListAdapter :
    ListAdapter<Song, SimiSongListAdapter.mViewHolder>(object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.id == newItem.id
        }
    }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimiSongListAdapter.mViewHolder {
        return mViewHolder(View.inflate(parent.context, R.layout.layout_songlist, null))
    }

    override fun onBindViewHolder(holder: SimiSongListAdapter.mViewHolder, position: Int) {
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

        fun bind(item: Song) {
            Glide.with(itemView.context).load(item.al.picUrl)
                .transform(CenterCrop(), RoundedCorners(10)).into(songIv)
            songSingerTv.text = "未知"
            songNameTv.text = item.name
        }
    }
}