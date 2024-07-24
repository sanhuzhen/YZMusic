package com.sanhuzhen.module.musicplayer.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.module.musicplayer.R
import com.sanhuzhen.module.musicplayer.bean.Ar
import com.therouter.TheRouter

class SingerAdapter : ListAdapter<Ar,SingerAdapter.mViewHolder>(object : DiffUtil.ItemCallback<Ar>(){
    override fun areItemsTheSame(oldItem: Ar, newItem: Ar): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Ar, newItem: Ar): Boolean {
        return oldItem.id == newItem.id
    }

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingerAdapter.mViewHolder {
        return mViewHolder(View.inflate(parent.context,R.layout.item_singer_rv,null))
    }

    override fun onBindViewHolder(holder: SingerAdapter.mViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class mViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val SingerName : TextView = itemView.findViewById(R.id.tv_singer)

        init {
            setOnClickListener()
        }

        fun setOnClickListener() {
            itemView.setOnClickListener {
                TheRouter.build("/songlist/singer")
                    .withString("SingerId", getItem(adapterPosition).id.toString())
                    .navigation()
            }
        }
        fun bind(item: Ar){
            SingerName.text = item.name
        }
    }


}