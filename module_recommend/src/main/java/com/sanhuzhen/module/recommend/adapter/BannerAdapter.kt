package com.sanhuzhen.module.recommend.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.module.home.R
import com.sanhuzhen.module.recommend.bean.Banner


class BannerAdapter() :
    ListAdapter<Banner, BannerAdapter.mViewHolder>(object : DiffUtil.ItemCallback<Banner>() {
        override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
            return oldItem.pic == newItem.pic && oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
            return oldItem == newItem
        }
    }) {


    inner class mViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private var bannerIv: ImageView = item.findViewById(R.id.banner_iv)

        init{
            initListener()
        }
        fun initListener() {
            bannerIv.setOnClickListener {
                //使用let，避免空指针
                getItem(adapterPosition).url?.let {
                    if ("http" in it){
                        val url = Uri.parse(it)
                        val customTabsIntent = CustomTabsIntent.Builder().build()
                        customTabsIntent.launchUrl(itemView.context, url)
                    }
                }

            }
        }
        fun bind(banner: Banner) {
            Glide.with(itemView.context).load(banner.pic)
                .transform(CenterCrop(), RoundedCorners(60)).into(bannerIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        )
    }

    override fun getItem(position: Int): Banner {
        return super.getItem(position % currentList.size)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE


    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}