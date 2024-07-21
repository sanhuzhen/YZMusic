package com.sanhuzhen.module.mine.adapter

import android.view.LayoutInflater
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
import com.sanhuzhen.module.mine.bean.Follow

class FocusRvAdapter :ListAdapter<Follow,FocusRvAdapter.OneHolder>(object :
    DiffUtil.ItemCallback<Follow>(){
    override fun areItemsTheSame(oldItem: Follow, newItem: Follow): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Follow, newItem: Follow): Boolean {
        return oldItem==newItem
    }

}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FocusRvAdapter.OneHolder {
        val itemView = LayoutInflater.from(parent.context)
        return OneHolder(itemView.inflate(R.layout.item_focus, parent, false))
    }

    override fun onBindViewHolder(holder: FocusRvAdapter.OneHolder, position: Int) {
        val focusData=getItem(position)
        holder.focusData(focusData)
    }
    inner class OneHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val focusName: TextView =itemView.findViewById(R.id.name_focus)
        val focusImg: ImageView =itemView.findViewById(R.id.iv_img)
        val focusVip: ImageView =itemView.findViewById(R.id.img_vip)
        val focusLogo: ImageView =itemView.findViewById(R.id.img_logo)
        fun focusData(item: Follow){
            focusName.text=item.nickname
            if (item.avatarUrl.isNotEmpty()){
                Glide.with(itemView.context)
                    .load(item.avatarUrl)
                    .transform(CenterCrop(), RoundedCorners(90))
                    .into(focusImg)
            }
            else{
                focusImg.setImageResource(R.drawable.ic_launcher_foreground)
            }
            if (item.vipRights.associator!= null){
                Glide.with(itemView.context)
                    .load(item.vipRights.associator.iconUrl)
                    .transform(CenterCrop(), RoundedCorners(1))
                    .into(focusVip)
            }
            else{
                focusVip.visibility=android.view.View.GONE
            }
            if (item.avatarDetail != null){
                Glide.with(itemView.context)
                    .load(item.avatarDetail.identityIconUrl)
                    .transform(CenterCrop(), RoundedCorners(90))
                    .into(focusLogo)
            }
            else{
                focusLogo.visibility=android.view.View.GONE

            }
        }
    }
}