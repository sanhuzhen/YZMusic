package com.example.module.search.adapter

import android.util.Log
import android.view.LayoutInflater
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
import com.example.module.search.R
import com.example.module.search.bean.Mv
import com.therouter.TheRouter

class MvRvAdapter :ListAdapter<Mv,MvRvAdapter.ThisHodler>(object :DiffUtil.ItemCallback<Mv>(){
    override fun areItemsTheSame(oldItem: Mv, newItem: Mv): Boolean {
        return oldItem.id==newItem.id&&oldItem.name==newItem.name&&oldItem.cover==newItem.cover&&oldItem.artists==newItem.artists
    }

    override fun areContentsTheSame(oldItem: Mv, newItem: Mv): Boolean {
        return oldItem.id==newItem.id&&oldItem.name==newItem.name&&oldItem.cover==newItem.cover&&oldItem.artists==newItem.artists
    }

}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvRvAdapter.ThisHodler {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.mv_rv,parent,false)
        return ThisHodler(itemView)
    }

    override fun onBindViewHolder(holder: MvRvAdapter.ThisHodler, position: Int) {
        val mvData=getItem(position)
        holder.mvData(mvData)
    }
    inner class ThisHodler(itemView: View): RecyclerView.ViewHolder(itemView){
        private val mvName: TextView =itemView.findViewById(R.id.mv_title)
        private val mvArtist: TextView =itemView.findViewById(R.id.mv_artist)
        private val mvCover: ImageView =itemView.findViewById(R.id.mv_img)
        init {
            MvClick()
        }
        fun MvClick(){
            itemView.setOnClickListener {
                TheRouter.build("/mvplayer/MvPlayerActivity").withString("id",getItem(adapterPosition).id.toString()).navigation()
                Log.d("MvAdapter","--------- ${getItem(adapterPosition).id}")
            }

        }
        fun mvData(mv: Mv){
            mvName.text=mv.name
            mvArtist.text=mv.artists[0].name
            if (mv.cover.isNotEmpty()){
                Glide.with(itemView.context).load(mv.cover).transform(CenterCrop(),
                    RoundedCorners(60)).into(mvCover)
            }
            else{
                mvCover.setImageResource(R.drawable.ic_launcher_foreground)
            }

        }
    }

}
