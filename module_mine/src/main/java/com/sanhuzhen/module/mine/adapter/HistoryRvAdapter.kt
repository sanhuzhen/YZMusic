package com.sanhuzhen.module.mine.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.lib.base.helper.SongDataHelper
import com.sanhuzhen.module.mine.R
import com.sanhuzhen.module.mine.bean.WeekData
import com.therouter.TheRouter

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
        private val HistoryElse:ImageView=itemView.findViewById(R.id.his_else)
        fun historyData(historyData: WeekData){
            if (historyData.song.al.picUrl.isNotEmpty()){
                Glide.with(itemView.context).load(historyData.song.al.picUrl)
                    .transform(CenterCrop(), RoundedCorners(60)).into(HistoryImg)
            }
            else{
                HistoryImg.setImageResource(R.drawable.ic_launcher_foreground)
            }
            HistoryImg.setOnClickListener {
                TheRouter.build("/musicplayer/musicplayerActivity")
                    .withObject("SongList",getItem(adapterPosition).song.id.toString())
            }
            HistorySong.text=historyData.song.name
            HistorySinger.text=historyData.song.ar[0].name
            HistoryElse.setOnClickListener{
                val popup = PopupMenu(itemView.context, HistoryElse)
                popup.menuInflater.inflate(R.menu.sample, popup.menu)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.share -> {
                            val shareIntent = Intent().apply {
                                val title = "我发现了一首比较不错的音乐，分享给你\n${historyData.song.name} -- ${historyData.song.ar[0].name}"
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, title)
                                type = "text/plain"
                            }
                            // 启动分享活动
                            startActivity(itemView.context,
                                Intent.createChooser(shareIntent,"选择分享应用"),null)
                            true
                        }
                        R.id.downloads -> {
                            val dpHelper= SongDataHelper(itemView.context,"song",1)
                            dpHelper.addBook(historyData.song.name,historyData.song.ar[0].name,historyData.song.id.toString(),historyData.song.al.picUrl)
                            true
                        }
                        else -> false
                    }
                }
                popup.show()
            }

        }
    }
}