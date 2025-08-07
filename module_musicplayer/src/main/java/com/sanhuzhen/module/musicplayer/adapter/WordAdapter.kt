package com.sanhuzhen.module.musicplayer.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.module.musicplayer.R
import com.sanhuzhen.module.musicplayer.bean.LyricLine



class WordAdapter() : ListAdapter<LyricLine, WordAdapter.LrcViewHolder>(object : DiffUtil.ItemCallback<LyricLine>() {
    override fun areItemsTheSame(oldItem: LyricLine, newItem: LyricLine): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: LyricLine, newItem: LyricLine): Boolean {
        return oldItem.timeMillis==newItem.timeMillis
    }
}){

    // 当前正在播放的歌词索引
    var currentPlayIndex: Int = -1

    // ViewHolder
    inner class LrcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.mWord)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LrcViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_songword, parent, false)
        return LrcViewHolder(view)
    }

    override fun onBindViewHolder(holder: LrcViewHolder, position: Int) {
        val entry = getItem(position)
        holder.textView.text = entry.text

        val context = holder.textView.context

        if (position == currentPlayIndex) {
            holder.textView.setTextColor(ContextCompat.getColor(context, R.color.lyric_highlight))
            holder.textView.setBackgroundColor(ContextCompat.getColor(context, R.color.lyric_highlight_bg))
        } else {
            holder.textView.setTextColor(ContextCompat.getColor(context, R.color.lyric_normal))
            holder.textView.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    // 更新当前播放的歌词位置
    fun updateCurrentPlayingIndex(newIndex: Int) {
        val oldIndex = currentPlayIndex
        currentPlayIndex = newIndex
        // 只刷新这两个 item（当前行 和 之前行）
        notifyItemChanged(oldIndex)
        notifyItemChanged(newIndex)
    }
}