package com.sanhuzhen.module.mvplayer.adapter

import android.graphics.Color
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
import com.sanhuzhen.module.mvplayer.R
import com.sanhuzhen.module.mvplayer.bean.Artist

class SingerRvAdapter :ListAdapter<Artist,SingerRvAdapter.MyViewHolder>(object :
    DiffUtil.ItemCallback<Artist>(){
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem==newItem
    }

}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= View.inflate(parent.context, R.layout.item_singer,null)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val singerData=getItem(position)
        holder.singerData(singerData)
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistName: TextView =itemView.findViewById(R.id.tv_singer)
        private val artistImg: ImageView =itemView.findViewById(R.id.iv_singer)
        fun singerData(singerData: Artist) {
            artistName.text=singerData.name

            if (singerData.img1v1Url.isNotEmpty()){
                Glide.with(itemView.context)
                    .load(singerData.img1v1Url)
                    .transform(CenterCrop(), RoundedCorners(90))
                    .into(artistImg)
            }

        }
    }
}