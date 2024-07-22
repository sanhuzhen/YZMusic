package com.sanhuzhen.module.songlist.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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
        }
    }
}