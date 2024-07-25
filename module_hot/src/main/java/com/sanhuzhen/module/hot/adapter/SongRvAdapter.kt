package com.sanhuzhen.module.hot.adapter

import android.content.ContentValues
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.lib.base.helper.SongDataHelper
import com.sanhuzhen.module.hot.R
import com.sanhuzhen.module.hot.bean.Track
import com.therouter.TheRouter

class SongRvAdapter: ListAdapter<Track, SongRvAdapter.SongHolder>(object :
    DiffUtil.ItemCallback<Track>(){
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem==newItem
    }

}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongRvAdapter.SongHolder {
        val itemView=View.inflate(parent.context, R.layout.item_songlist,null)
        return SongHolder(itemView)
    }

    override fun onBindViewHolder(holder: SongRvAdapter.SongHolder, position: Int) {
        val songListData=getItem(position)
        holder.bind(songListData)
    }
    inner class SongHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val songName: TextView=itemView.findViewById(R.id.song_name)
        private val songSinger:TextView=itemView.findViewById(R.id.song_singer)
        private val songImg:ImageView=itemView.findViewById(R.id.song_img)
        private val songElse:ImageView=itemView.findViewById(R.id.song_else)
        fun bind(track: Track){
            if (track.al.picUrl.isNotEmpty()){
                Glide.with(itemView.context)
                    .load(track.al.picUrl)
                    .transform(CenterCrop(), RoundedCorners(30))
                    .into(songImg)
            }else{
                songImg.setImageResource(R.drawable.ic_launcher_background)
            }
            itemView.setOnClickListener {
                TheRouter.build("/musicplayer/musicplayerActivity")
                    .withObject("SongList", arrayListOf(getItem(adapterPosition).id.toString()))
                    .navigation()
            }
            songName.text=track.name
            songSinger.text=track.ar[0].name
            songElse.setOnClickListener{
                val popup = PopupMenu(itemView.context, songElse)
                popup.menuInflater.inflate(R.menu.sample, popup.menu)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.share -> {
                            val shareIntent = Intent().apply {
                                val title = "我发现了一首比较不错的音乐，分享给你\n${track.name} -- ${track.ar[0].name}"
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
                            val dpHelper=SongDataHelper(itemView.context,"song",1)
                            dpHelper.addBook(track.name,track.ar[0].name,track.id.toString(),track.al.picUrl)
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