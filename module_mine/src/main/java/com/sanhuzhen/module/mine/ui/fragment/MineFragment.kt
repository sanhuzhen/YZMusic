package com.sanhuzhen.module.mine.ui.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.mine.R
import com.sanhuzhen.module.mine.databinding.FragmentMineBinding
import com.sanhuzhen.module.mine.ui.activity.DownloadsActivity
import com.sanhuzhen.module.mine.ui.activity.FavouriteActivity
import com.sanhuzhen.module.mine.ui.activity.FocusActivity
import com.sanhuzhen.module.mine.ui.activity.HistoryActivity
import com.sanhuzhen.module.mine.ui.activity.NetActivity
import com.sanhuzhen.module.mine.viewmodel.BaseViewModel
import com.therouter.TheRouter
import com.therouter.router.Autowired
import com.therouter.router.Route
import java.io.File


class MineFragment : BaseFragment<FragmentMineBinding>() {
    var MyId: Long? = null
    val mBaseViewModel: BaseViewModel by lazy { ViewModelProvider(this)[BaseViewModel::class.java] }

    companion object {
        const val REQUEST_CODE_CHOOSE_PHOTO = 1
    }

    override fun getViewBinding(): FragmentMineBinding {
        return FragmentMineBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        getBase()
        getSp()
        btnMine()
        getCloud()
        getFocus()
        getFavourite()
        getHistory()
        getElse()
        getDownloads()
        logout()
    }

    fun logout() {
        mBinding.logout.setOnClickListener {
            val builder = AlertDialog.Builder(this.requireContext())
            builder.setTitle("询问")
            builder.setMessage("是否要退出登录")
            builder.setPositiveButton("确定") { dialog, _ ->
                val sp = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
                val edit = sp.edit()
                edit.remove("id")
                edit.apply()
                TheRouter.build("/login/LoginActivity").navigation()
                dialog.dismiss()
            }
            builder.setNegativeButton("取消") { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

    }

    fun getBase() {
        getSp()
        if (MyId?.toInt() != 0) {
            mBaseViewModel.getBase(MyId!!)
            mBaseViewModel.mBase.observe(this) {
                if (it.profile.avatarUrl != null) {
                    Glide.with(this.requireContext())
                        .load(it.profile.avatarUrl)
                        .transform(CenterCrop(), RoundedCorners(90))
                        .into(mBinding.imageView)
                }
                mBinding.textView.text = it.profile.nickname
            }
        }
    }

    fun getSp() {
        val sp = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        MyId = sp.getLong("id", 0)
        Log.d("you", "${MyId}")
    }

    fun btnMine() {
        mBinding.layoutMine.setOnClickListener {
            if (MyId?.toInt() == 0) {
                Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
                TheRouter.build("/login/LoginActivity").navigation()
            } else {
                val builder = AlertDialog.Builder(this.requireContext())
                builder.setTitle("询问")
                builder.setMessage("是否要对头像做出更改")
                builder.setPositiveButton("确定") { dialog, _ ->
                    changeImg()
                    mBinding.imageView.visibility = View.GONE
                    mBinding.replyImg.visibility = View.VISIBLE
                    dialog.dismiss()
                }
                builder.setNegativeButton("取消") { dialog, _ ->
                    dialog.dismiss()
                }
                val dialog = builder.create()
                dialog.show()
            }
        }
    }

    fun changeImg() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_PHOTO)
    }

    fun getCloud() {
        mBinding.musicNet.setOnClickListener {
            if (MyId?.toInt() == 0) {
                Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
                TheRouter.build("/login/LoginActivity").navigation()
            } else {
                startActivity(Intent(this.requireContext(), NetActivity::class.java))
            }
        }
    }

    fun getFocus() {
        mBinding.myFocus.setOnClickListener {
            if (MyId?.toInt() == 0) {
                Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
                TheRouter.build("/login/LoginActivity").navigation()
            } else {
                startActivity(Intent(this.requireContext(), FocusActivity::class.java))
            }
        }
    }

    fun getFavourite() {
        mBinding.musicFavourite.setOnClickListener {
            if (MyId?.toInt() == 0) {
                Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
                TheRouter.build("/login/LoginActivity").navigation()
            } else {
                startActivity(Intent(this.requireContext(), FavouriteActivity::class.java))
            }
        }
    }

    fun getHistory() {
        mBinding.musicHistory.setOnClickListener {
            if (MyId?.toInt() == 0) {
                Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
                TheRouter.build("/login/LoginActivity").navigation()
            } else {
                startActivity(Intent(this.requireContext(), HistoryActivity::class.java))
            }
        }
    }

    fun getElse() {
        mBinding.musicElse.setOnClickListener {
            if (MyId?.toInt() == 0) {
                Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
                TheRouter.build("/login/LoginActivity").navigation()
            } else {
                Toast.makeText(this.requireContext(), "功能正在开发中", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getDownloads() {
        mBinding.musicDownloads.setOnClickListener {
            if (MyId?.toInt() == 0) {
                Toast.makeText(this.requireContext(), "请先登录", Toast.LENGTH_SHORT).show()
                TheRouter.build("/login/LoginActivity").navigation()
            } else {
                startActivity(Intent(this.requireContext(), DownloadsActivity::class.java))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_CHOOSE_PHOTO -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    data.data?.let { uri ->
                        val bitmap = getBitmapFromUri(uri)
                        mBinding.replyImg.setImageBitmap(bitmap)

                    }
                }
            }
        }
    }

    fun getBitmapFromUri(uri: Uri) =
        this.requireActivity().contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it)
        }

    override fun onResume() {
        super.onResume()
        getSp()
        getBase()
        btnMine()
        getCloud()
        getFocus()
        getFavourite()
        getHistory()
        getElse()
        getDownloads()
        logout()
    }
}