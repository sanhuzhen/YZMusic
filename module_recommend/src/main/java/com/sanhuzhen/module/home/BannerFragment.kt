package com.sanhuzhen.module.home

import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.home.databinding.FragmentBannerBinding

class BannerFragment(val pic: String, val url: String?) : BaseFragment<FragmentBannerBinding>() {
    override fun getViewBinding(): FragmentBannerBinding {
        return FragmentBannerBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        Glide.with(requireActivity()).load(pic)
            .transform(CenterCrop(), RoundedCorners(60))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(mBinding.bannerIv)

        Log.d("BannerFragment", "pic:$pic")
        mBinding.bannerIv.setOnClickListener {
            if (url != null && "http" in url) {
                openCustomTab(url)
            }
        }
    }

    private fun openCustomTab(url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(requireActivity(), Uri.parse(url))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Glide.with(requireActivity()).clear(mBinding.bannerIv)
    }
}