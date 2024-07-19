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
import com.sanhuzhen.module.mine.bean.WeekData

class HistoryRvAdapter : ListAdapter<WeekData,HistoryRvAdapter.HistoryHolder>(object :
    DiffUtil.ItemCallback<WeekData>() {
    override fun areItemsTheSame(oldItem: WeekData, newItem: WeekData): Boolean {
        return oldItem.song==newItem.song
    }

    override fun areContentsTheSame(oldItem: WeekData, newItem: WeekData): Boolean {
        return oldItem.song==newItem.song
    }
}){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_history,parent,false)
        return HistoryHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val historyData=getItem(position)
        holder.historyData(historyData)
    }
    inner class HistoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val HistoryImg: ImageView =itemView.findViewById(R.id.iv_song_img)
        private val HistorySong: TextView =itemView.findViewById(R.id.tv_song_name)
        private val HistorySinger: TextView =itemView.findViewById(R.id.tv_singer_name)
        fun historyData(historyData: WeekData){
            if (historyData.song.al.picUrl.isNotEmpty()){
                Glide.with(itemView.context).load(historyData.song.al.picUrl)
                    .transform(CenterCrop(), RoundedCorners(60)).into(HistoryImg)
            }
            else{
                HistoryImg.setImageResource(R.drawable.ic_launcher_foreground)
            }
            HistorySong.text=historyData.song.name
            HistorySinger.text=historyData.song.ar[0].name

        }
    }
}