package com.sanhuzhen.module.musicplayer.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sanhuzhen.module.musicplayer.R
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.module.musicplayer.bean.Comment

class CommentAdapter : PagingDataAdapter<Comment, CommentAdapter.CommentViewHolder>(object :
    DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.content == newItem.content
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.commentId == newItem.commentId
    }
}) {
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(View.inflate(parent.context, R.layout.rv_item_comment, null))
    }

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iv_user: ImageView = itemView.findViewById(R.id.iv_user)
        private val tv_user: TextView = itemView.findViewById(R.id.tv_userName)
        private val tv_content: TextView = itemView.findViewById(R.id.tv_content)
        private val tv_time: TextView = itemView.findViewById(R.id.tv_time)

        fun bind(item: Comment) {
            tv_user.text = item.user.nickname
            tv_content.text = item.content
            if (item.needDisplayTime) {
                tv_time.visibility = View.VISIBLE
                tv_time.text = item.timeStr
            } else {
                tv_time.visibility = View.GONE
            }
            Glide.with(iv_user.context).load(item.user.avatarUrl)
                .transform(CenterCrop(), RoundedCorners(360)).into(iv_user)
        }
    }


}