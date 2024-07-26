package com.sanhuzhen.module.songlist.adapter

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.lib.base.helper.SongDataHelper
import com.sanhuzhen.module.songlist.R
import com.sanhuzhen.module.songlist.bean.Track
import com.therouter.TheRouter

class SongListAdapter :
    ListAdapter<Track, SongListAdapter.SongListViewHolder>(object : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.ar == newItem.ar && oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem.id == newItem.id
        }
    }) {
    override fun onBindViewHolder(holder: SongListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: android.view.ViewGroup,
        viewType: Int
    ): SongListViewHolder {
        return SongListViewHolder(View.inflate(parent.context, R.layout.item_song_rv, null))
    }

    inner class SongListViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        private val mBinding =
            com.sanhuzhen.module.songlist.databinding.ItemSongRvBinding.bind(itemView)

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

        fun bind(track: Track) {
            mBinding.apply {
                tvSong.text = track.name
                var str = ""
                for (i in track.ar) {
                    str = str + i.name + " "
                }
                tvArtist.text = str
            }
            Glide.with(itemView.context).load(track.al.picUrl)
                .transform(CenterCrop(), RoundedCorners(10)).into(mBinding.ivSong)
            mBinding.ivElse.setOnClickListener {
                val popup = PopupMenu(itemView.context, mBinding.ivElse)
                popup.menuInflater.inflate(R.menu.sample, popup.menu)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.share -> {
                            val shareIntent = Intent().apply {
                                val title =
                                    "我发现了一首比较不错的音乐，分享给你\n${track.name} -- ${track.ar[0].name}"
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, title)
                                type = "text/plain"
                            }
                            // 启动分享活动
                            startActivity(
                                itemView.context,
                                Intent.createChooser(shareIntent, "选择分享应用"), null
                            )
                            true
                        }

                        R.id.downloads -> {
                            val dpHelper = SongDataHelper(itemView.context, "song", 1)
                            dpHelper.addBook(
                                track.name,
                                track.ar[0].name,
                                track.id.toString(),
                                track.al.picUrl
                            )
                            Toast.makeText(itemView.context, "下载成功", Toast.LENGTH_SHORT).show()
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