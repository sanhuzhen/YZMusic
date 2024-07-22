package com.sanhuzhen.module.mine.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.module.mine.R
import com.sanhuzhen.module.mine.bean.Data

class CloudRvAdapter:ListAdapter<Data,CloudRvAdapter.MyViewHolder> (object :
    DiffUtil.ItemCallback<Data>(){
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem==newItem
    }
}){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = android.view.LayoutInflater.from(parent.context)
        return MyViewHolder(itemView.inflate(R.layout.item_cloud, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cloudData=getItem(position)
        holder.cloudData(cloudData)
    }
    inner class MyViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val cloudName:TextView=itemView.findViewById(R.id.tv_song_name)
        val cloudImg:ImageView=itemView.findViewById(R.id.iv_song_img)
        fun cloudData(data: Data){
            if(data.simpleSong.al.picUrl.isNotEmpty()){
                Glide.with(itemView.context)
                    .load(data.simpleSong.al.picUrl)
                    .transform(CenterCrop(), RoundedCorners(60))
                    .into(cloudImg)
            }
            else{
                cloudImg.setImageResource(R.drawable.ic_launcher_foreground)
            }
            cloudName.text=data.simpleSong.name
        }
    }
}