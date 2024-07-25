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
import com.sanhuzhen.module.songlist.bean.Mv
import com.therouter.TheRouter

class MvAdapter : ListAdapter<Mv, MvAdapter.mViewHolder>(object : DiffUtil.ItemCallback<Mv>() {
    override fun areItemsTheSame(oldItem: Mv, newItem: Mv): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Mv, newItem: Mv): Boolean {
        return oldItem.id == newItem.id
    }

}) {

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mvIv: ImageView = itemView.findViewById(R.id.mv_img)
        private val mvNameTv: TextView = itemView.findViewById(R.id.mv_title)
        private val mvSingerTv: TextView = itemView.findViewById(R.id.mv_artist)

        init {
            setOnClickListener()
        }

        fun setOnClickListener() {
            itemView.setOnClickListener {
                TheRouter.build("/mvplayer/MvPlayerActivity")
                    .withObject("id", getItem(adapterPosition).id.toString())
                    .navigation()
            }
        }

        fun bind(item: Mv) {
            Glide.with(itemView.context).load(item.imgurl)
                .transform(CenterCrop(), RoundedCorners(60)).into(mvIv)
            mvNameTv.text = item.name
            mvSingerTv.text = item.artistName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(View.inflate(parent.context, R.layout.mv_rv, null))
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}