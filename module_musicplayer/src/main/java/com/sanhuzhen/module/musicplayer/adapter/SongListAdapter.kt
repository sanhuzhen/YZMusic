package com.sanhuzhen.module.musicplayer.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.module.musicplayer.R
import com.sanhuzhen.module.musicplayer.bean.Song
import com.sanhuzhen.module.musicplayer.helper.OnItemClickListener

class SongListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Song, SongListAdapter.mViewHolder>(object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.name == newItem.name && oldItem.ar == newItem.ar
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(View.inflate(parent.context, R.layout.rv_item_songlist, null))
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.setOnClickListener(listener, position)
    }

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val SongName: TextView = itemView.findViewById(R.id.tv_songlist_name)
        private val SingerName: TextView = itemView.findViewById(R.id.tv_songlist_singer)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun setOnClickListener(listener: OnItemClickListener, position: Int) {
            itemView.setOnClickListener {
                listener.onItemClick(position)
            }
        }

        fun bind(item: Song) {
            SongName.text = item.name
            var singerName = ""
            for (i in item.ar) {
                singerName += i.name
                if (i != item.ar[item.ar.size - 1]) {
                    singerName += "/"
                }
            }
            SingerName.text = singerName

        }
    }
}