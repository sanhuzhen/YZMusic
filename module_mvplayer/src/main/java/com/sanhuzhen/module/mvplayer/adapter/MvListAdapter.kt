package com.sanhuzhen.module.mvplayer.adapter

import android.content.Intent
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
import com.sanhuzhen.module.mvplayer.MvPlayerActivity
import com.sanhuzhen.module.mvplayer.R
import com.sanhuzhen.module.mvplayer.bean.Mv
import com.therouter.TheRouter

class MvListAdapter :
    ListAdapter<Mv, MvListAdapter.mViewHolder>(object : DiffUtil.ItemCallback<Mv>() {
        override fun areItemsTheSame(oldItem: Mv, newItem: Mv): Boolean {
            return oldItem.cover == newItem.cover
        }

        override fun areContentsTheSame(oldItem: Mv, newItem: Mv): Boolean {
            return oldItem.id == newItem.id
        }
    }) {
    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mvCover: ImageView = itemView.findViewById(R.id.mv_img)
        private val mvTitle: TextView = itemView.findViewById(R.id.mv_title)

        init {
            initListener()
        }

        private fun initListener() {
            itemView.setOnClickListener {
                //使用let，避免空指针
                getItem(adapterPosition).let {
                    val intent = Intent(itemView.context,MvPlayerActivity::class.java)
                    intent.putExtra("id",it.id.toString())
                    itemView.context.startActivity(intent)
                }
            }
        }

        fun bind(mv: Mv) {
            Glide.with(itemView.context).load(mv.cover).transform(CenterCrop(), RoundedCorners(60))
                .into(mvCover)
            mvTitle.text = mv.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvListAdapter.mViewHolder {
        return mViewHolder(View.inflate(parent.context, R.layout.item_mv_rv, null))
    }

    override fun onBindViewHolder(holder: MvListAdapter.mViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}