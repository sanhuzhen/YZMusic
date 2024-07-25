package com.sanhuzhen.module.mine.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.lib.base.helper.SongDataHelper
import com.sanhuzhen.module.mine.R
import com.sanhuzhen.module.mine.bean.SongData
import com.therouter.TheRouter

class DownloadsRvAdapter:ListAdapter<SongData, DownloadsRvAdapter.SongViewHolder>(object :
    DiffUtil.ItemCallback<SongData>(){
    override fun areItemsTheSame(oldItem: SongData, newItem: SongData): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: SongData, newItem: SongData): Boolean {
        return oldItem==newItem
    }
}) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DownloadsRvAdapter.SongViewHolder {
        val itemView=
            LayoutInflater.from(parent.context).inflate(R.layout.item_downloads,parent,false)
        return SongViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DownloadsRvAdapter.SongViewHolder, position: Int) {
        val songData=getItem(position)
        holder.songData(songData)
    }

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val songName: TextView =itemView.findViewById(R.id.song_name)
        private val singerName: TextView =itemView.findViewById(R.id.song_singer)
        private val delete: ImageView =itemView.findViewById(R.id.delete)
        private val songItem:FrameLayout=itemView.findViewById(R.id.song_downloads)
        @SuppressLint("NotifyDataSetChanged")
        fun songData(songData: SongData){
            songItem.setOnClickListener {
                TheRouter.build("/musicplayer/musicplayerActivity").withString("songId",songData.id).navigation()
            }
            songName.apply {
                text=songData.name
            }
            singerName.apply {
                text=songData.singer
            }
            delete.setOnClickListener {
                val dbHelper= SongDataHelper(itemView.context,"song",1)
                dbHelper.deleteBook(songData.id)
                val db = dbHelper.readableDatabase
                val cursor = db.query("Book", null, null, null, null, null, null)
                val contacts = mutableListOf<SongData>()

                with(cursor) {
                    while (moveToNext()) {
                        val id = getString(getColumnIndexOrThrow("songId"))
                        val name = getString(getColumnIndexOrThrow("name"))
                        val singer = getString(getColumnIndexOrThrow("singer"))
                        contacts.add(SongData(id, name,singer,"0"))
                    }
                }
                cursor.close()
                submitList(contacts)

            }
        }
    }
}