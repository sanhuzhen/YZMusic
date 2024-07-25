package com.sanhuzhen.module.mine.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
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
import com.sanhuzhen.module.mine.bean.PlaylistX
import com.sanhuzhen.module.mine.bean.Track
import com.therouter.TheRouter

class FavouriteRvAdapter:ListAdapter<Track,FavouriteRvAdapter.FavHolder>(object :
    DiffUtil.ItemCallback<Track>(){
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }
}) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_favourite,parent,false)
        return FavHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavHolder, position: Int) {
        val favouriteData=getItem(position)
        holder.favouriteData(favouriteData)
    }
    inner class FavHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val FavImg: ImageView =itemView.findViewById(R.id.iv_song_img)
        val FavSong: TextView =itemView.findViewById(R.id.tv_song_name)
        val FavSinger: TextView =itemView.findViewById(R.id.tv_singer_name)
        val FavElse: ImageView =itemView.findViewById(R.id.iv_else)
        val FavItem:FrameLayout=itemView.findViewById(R.id.song_fav)
        fun favouriteData(favouriteData: Track){
            if (favouriteData.al.picUrl.isNotEmpty()){
                Glide.with(itemView.context).load(favouriteData.al.picUrl)
                    .transform(CenterCrop(), RoundedCorners(60)).into(FavImg)
            }
            else{
                FavImg.setImageResource(R.drawable.ic_launcher_foreground)
            }
            itemView.setOnClickListener {
                TheRouter.build("/musicplayer/musicplayerActivity")
                    .withObject("SongList", arrayListOf(getItem(adapterPosition).id.toString()))
                    .navigation()
            }
            FavSong.text=favouriteData.name
            FavSinger.text=favouriteData.ar[0].name
            FavElse.setOnClickListener {
                val popup = PopupMenu(itemView.context, FavElse)
                popup.menuInflater.inflate(R.menu.sample, popup.menu)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.share -> {
                            val shareIntent = Intent().apply {
                                val title = "我发现了一首比较不错的音乐，分享给你\n${favouriteData.name} -- ${favouriteData.ar[0].name}"
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, title)
                                type = "text/plain"
                            }
                            // 启动分享活动
                            startActivity(itemView.context,Intent.createChooser(shareIntent,"选择分享应用"),null)
                            true
                        }
                        R.id.downloads -> {
                            val dbHelper= SongDataHelper(itemView.context,"song",1)
                            dbHelper.addBook(favouriteData.name,favouriteData.ar[0].name,favouriteData.id.toString(),favouriteData.al.picUrl)
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