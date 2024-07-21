package com.sanhuzhen.module.recommend.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.module.home.R
import com.sanhuzhen.module.recommend.bean.Resource
import com.therouter.TheRouter

class HomePlayListAdapter(private val fragment: Fragment) : ListAdapter<Resource, HomePlayListAdapter.mViewHolder>(object :
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


    override fun onBindViewHolder(holder: HomePlayListAdapter.mViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class mViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private var playlistIv: ImageView = item.findViewById(R.id.playlist_iv)
        private var playlistNameTv: TextView = item.findViewById(R.id.playlist_tv)

        init {
            initListener()
        }
        fun initListener() {
            itemView.setOnClickListener {
                TheRouter.build("/songlist/songListActivity")
                    .withString("id", getItem(adapterPosition).resourceId)
                    .withString("alTv",getItem(adapterPosition).uiElement.image.imageUrl)
                    .navigation()
            }
        }
        fun bind(resource: Resource) {
            playlistNameTv.text = resource.uiElement.mainTitle.title
            Glide.with(itemView.context).load(resource.uiElement.image.imageUrl)
                .transform(CenterCrop(), RoundedCorners(60)).into(playlistIv)
        }
    }

}