package com.sanhuzhen.module.home.helper
import android.view.View
import androidx.viewpager2.widget.ViewPager2

class ZoomOutPageTransformer : ViewPager2.PageTransformer {
    //页面最小的缩放比例
    private val MIN_SCALE = 0.85f
    //页面最小的透明度
    private val MIN_ALPHA = 0.5f

    //修改页面的视觉效果
    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val pageHeight = page.height

        when {
            position < -1 -> {
                page.alpha = 0f
            }
            position <= 1 -> {
                val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                val vertMargin = pageHeight * (1 - scaleFactor) / 2
                val horzMargin = pageWidth * (1 - scaleFactor) / 2
                if (position < 0) {
                    page.translationX = horzMargin - vertMargin / 2
                } else {
                    page.translationX = -horzMargin + vertMargin / 2
                }

                page.scaleX = scaleFactor
                page.scaleY = scaleFactor


                page.alpha = MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)
            }
            else -> {
                page.alpha = 0f
            }
        }
    }
}