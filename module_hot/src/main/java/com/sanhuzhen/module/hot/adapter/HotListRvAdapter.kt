package com.sanhuzhen.module.hot.adapter

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.module.hot.R
import com.sanhuzhen.module.hot.bean.Playlists
import com.sanhuzhen.module.hot.ui.activity.DetailActivity

class HotListRvAdapter: ListAdapter<Playlists, HotListRvAdapter.HotListHolder>(object :
    DiffUtil.ItemCallback<Playlists>(){
    override fun areItemsTheSame(oldItem: Playlists, newItem: Playlists): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Playlists, newItem: Playlists): Boolean {
        return oldItem==newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotListHolder {
        val itemView = View.inflate(parent.context, R.layout.item_hotlist, null)
        return HotListHolder(itemView)
    }

    override fun onBindViewHolder(holder: HotListHolder, position: Int) {
        val hotListData = getItem(position)
        holder.hotListData(hotListData)
    }
    inner class HotListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val hotListImg: ImageView = itemView.findViewById(R.id.iv_hotlist)
        private val hotListName: TextView = itemView.findViewById(R.id.tv_hotlist)
        private val hotList: LinearLayout = itemView.findViewById(R.id.ll_hotlist)
        fun hotListData(data: Playlists) {
            if (data.coverImgUrl.isNotEmpty()) {
                Glide.with(itemView.context)
                    .load(data.coverImgUrl)
                    .transform(CenterCrop(), RoundedCorners(30))
                    .into(hotListImg)
            } else {
                hotListImg.setImageResource(R.drawable.ic_launcher_background)
            }
            hotListName.text = data.name
            hotList.setOnClickListener{
//                点击事件 val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        activity,
//                        // 这里需要是Activity中的ImageView，而不是Fragment中的（除非Fragment的根视图就是ImageView）
//                        // 实际应用中，你可能需要在Activity中设置共享元素
//                        imageViewInActivity, // 假设这是你在Activity中定义的ImageView
//                        "example_transition")
                val IntentHot= Intent(itemView.context, DetailActivity::class.java)
                IntentHot.putExtra("id",data.id)
                IntentHot.putExtra("name",data.name)
                IntentHot.putExtra("img",data.coverImgUrl)
                itemView.context.startActivity(IntentHot)
            }
        }
    }
}