package com.sanhuzhen.module.home.adapter

import android.content.Context
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
import com.sanhuzhen.module.home.R
import com.sanhuzhen.module.home.bean.Resource

class HomePlayListAdapter(private val context: Context,private val itemClickListener: OnItemClickListener) : ListAdapter<Resource, HomePlayListAdapter.mViewHolder>(object :
    DiffUtil.ItemCallback<Resource>() {
    override fun areItemsTheSame(oldItem: Resource, newItem: Resource): Boolean {
        return oldItem.uiElement == newItem.uiElement
    }

    override fun areContentsTheSame(oldItem: Resource, newItem: Resource): Boolean {
        return oldItem.resourceId == newItem.resourceId
    }

}) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePlayListAdapter.mViewHolder {
        return mViewHolder(
            View.inflate(
                parent.context,
                R.layout.homepageslideplaylist_rv_item,
                null
            )
        )
    }
    //设置接口，进行点击事件
    interface OnItemClickListener {
        fun onItemClick(item:Resource)
    }

    override fun onBindViewHolder(holder: HomePlayListAdapter.mViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class mViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private var playlistIv: ImageView = item.findViewById(R.id.playlist_iv)
        private var playlistNameTv: TextView = item.findViewById(R.id.playlist_tv)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(getItem(position))
                }
            }
        }
        fun bind(resource: Resource) {
            playlistNameTv.text = resource.uiElement.mainTitle.title
            Glide.with(itemView.context).load(resource.uiElement.image.imageUrl)
                .transform(CenterCrop(), RoundedCorners(60)).into(playlistIv)
        }
    }

}