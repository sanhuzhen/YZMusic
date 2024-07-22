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
import com.sanhuzhen.module.hot.bean.ListY
import com.sanhuzhen.module.hot.ui.activity.DetailActivity

class TopListRvAdapter:ListAdapter<ListY,TopListRvAdapter.TopListHolder> (object :
    DiffUtil.ItemCallback<ListY>(){
    override fun areItemsTheSame(oldItem: ListY, newItem: ListY): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: ListY, newItem: ListY): Boolean {
        return oldItem==newItem
    }

}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopListRvAdapter.TopListHolder {
        val itemView= View.inflate(parent.context, R.layout.item_toplist,null)
        return TopListHolder(itemView)
    }

    override fun onBindViewHolder(holder: TopListRvAdapter.TopListHolder, position: Int) {
        val topListData = getItem(position)
        holder.topListData(topListData)
    }
    inner class TopListHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val topListImg: ImageView =itemView.findViewById(R.id.iv_toplist)
        private val topListName: TextView =itemView.findViewById(R.id.tv_toplist)
        private val TopList:LinearLayout=itemView.findViewById(R.id.ll_toplist)
        fun topListData(data: ListY){
            if (data.coverImgUrl.isNotEmpty()){
                Glide.with(itemView.context)
                    .load(data.coverImgUrl)
                    .transform(CenterCrop(), RoundedCorners(30))
                    .into(topListImg)
            }
            else {
                topListImg.setImageResource(R.drawable.ic_launcher_background)
            }
            topListName.text=data.name

            TopList.setOnClickListener {
                //点击事件
                val IntentTop=Intent(itemView.context, DetailActivity::class.java)
                IntentTop.putExtra("id",data.id)
                IntentTop.putExtra("name",data.name)
                IntentTop.putExtra("img",data.coverImgUrl)
                itemView.context.startActivity(IntentTop)
            }
        }
    }
}