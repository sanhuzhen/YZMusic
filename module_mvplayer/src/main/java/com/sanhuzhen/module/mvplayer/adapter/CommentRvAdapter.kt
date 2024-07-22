package com.sanhuzhen.module.mvplayer.adapter

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
import com.sanhuzhen.module.mvplayer.bean.Comment

class CommentRvAdapter:ListAdapter<Comment,CommentRvAdapter.ThisViewHolder>(object :
    DiffUtil.ItemCallback<Comment>(){
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem==newItem
    }
}) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentRvAdapter.ThisViewHolder {
        val itemView=View.inflate(parent.context, R.layout.item_comment,null)
        return ThisViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentRvAdapter.ThisViewHolder, position: Int) {
        val commentData=getItem(position)
        holder.commentData(commentData)
    }
    inner class ThisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iv_user: ImageView = itemView.findViewById(R.id.iv_user)
        private val tv_user: TextView = itemView.findViewById(R.id.tv_userName)
        private val tv_content: TextView = itemView.findViewById(R.id.tv_content)
        private val tv_time: TextView = itemView.findViewById(R.id.tv_time)
        fun commentData(commentData: Comment) {
            tv_user.text=commentData.user.nickname
            tv_content.text=commentData.content
            if (commentData.needDisplayTime){
                tv_time.visibility=View.VISIBLE
                tv_time.text=commentData.timeStr
            }else{
                tv_time.visibility=View.GONE
            }
            Glide.with(itemView.context)
                .load(commentData.user.avatarUrl)
                .transform(CenterCrop(),RoundedCorners(90))
                .into(iv_user)
        }
    }
}