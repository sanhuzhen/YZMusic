package com.sanhuzhen.module.hot.adapter

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
import com.sanhuzhen.module.hot.R
import com.sanhuzhen.module.hot.bean.Artist

class SingerRvAdapter:ListAdapter<Artist,SingerRvAdapter.ThisHolder> (object :
    DiffUtil.ItemCallback<Artist>(){
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem==newItem
    }

}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingerRvAdapter.ThisHolder {
        val itemView= View.inflate(parent.context, R.layout.item_singer,null)
        return ThisHolder(itemView)
    }

    override fun onBindViewHolder(holder: SingerRvAdapter.ThisHolder, position: Int) {
        val artistData = getItem(position)
        holder.artistData(artistData)
    }
    inner class ThisHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistName: TextView =itemView.findViewById(R.id.tv_singer)
        private val artistImg: ImageView =itemView.findViewById(R.id.iv_singer)
        fun artistData(data: Artist){
            artistName.text=data.name
            if (data.picUrl.isNotEmpty()){
                Glide.with(itemView.context)
                    .load(data.picUrl)
                    .transform(CenterCrop(), RoundedCorners(90))
                    .into(artistImg)
            }
        }
    }
}