package com.sanhuzhen.module.mine.ui.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.mine.R
import com.sanhuzhen.module.mine.databinding.FragmentMineBinding
import com.sanhuzhen.module.mine.ui.activity.FavouriteActivity
import com.sanhuzhen.module.mine.ui.activity.HistoryActivity
import com.sanhuzhen.module.mine.viewmodel.BaseViewModel
import com.therouter.TheRouter
import com.therouter.router.Autowired
import com.therouter.router.Route


class MineFragment :BaseFragment<FragmentMineBinding>(){
    var MyId:Long?=null
    val mBaseViewModel: BaseViewModel by lazy { ViewModelProvider(this)[BaseViewModel::class.java] }
    override fun getViewBinding(): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        getBase()
        getMine()
        getSp()
        btnMine()
        getCloud()
        getFocus()
        getFavourite()
        getHistory()
    }
    fun getBase(){
        getSp()
        if (MyId?.toInt() !=0){
            mBaseViewModel.getBase(MyId!!)
            mBaseViewModel.mBase.observe(this) {
                if (it.profile.avatarUrl != null) {
                    Glide.with(this.requireContext())
                        .load(it.profile.avatarUrl)
                        .transform(CenterCrop(), RoundedCorners(60))
                        .into(mBinding.imageView)
                }
                mBinding.textView.text = it.profile.nickname
            }
        }
   }

    fun getMine(){
        if (MyId?.toInt() ==0){
            Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
            TheRouter.build("/login/LoginActivity").navigation()
        }
    }
    fun getSp(){
    val sp = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
    MyId=sp.getLong("id",0)
    }
    fun btnMine(){
        mBinding.layoutMine.setOnClickListener {
            if (MyId?.toInt() ==0) {
                Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
                TheRouter.build("/login/LoginActivity").navigation()
            }
            else{
                Toast.makeText(this.requireContext(), "功能正在开发中", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun getCloud(){
        mBinding.musicNet.setOnClickListener {
            if (MyId?.toInt()==0) {
                Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
                TheRouter.build("/login/LoginActivity").navigation()
            }
            else{
                Toast.makeText(this.requireContext(), "功能正在开发中", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun getFocus(){
        mBinding.myFocus.setOnClickListener {
            if (MyId?.toInt()==0) {
                Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
                TheRouter.build("/login/LoginActivity").navigation()
            }
            else{
                Toast.makeText(this.requireContext(), "功能正在开发中", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun getFavourite(){
        mBinding.musicFavourite.setOnClickListener {
            if (MyId?.toInt()==0) {
                Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
                TheRouter.build("/login/LoginActivity").navigation()
            }
            else{
                startActivity(Intent(this.requireContext(), FavouriteActivity::class.java))
            }
        }
    }
    fun getHistory(){
        mBinding.musicHistory.setOnClickListener {
            if (MyId?.toInt()==0) {
                Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
                TheRouter.build("/login/LoginActivity").navigation()
            }
            else{
                startActivity(Intent(this.requireContext(), HistoryActivity::class.java))
            }
        }
    }
}