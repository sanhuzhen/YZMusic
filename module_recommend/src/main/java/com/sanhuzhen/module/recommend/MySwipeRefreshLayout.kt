package com.sanhuzhen.module.recommend

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.math.abs

/**
 * @description:
 * @author: sanhuzhen
 * @date:
 */
class MySwipeRefreshLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : SwipeRefreshLayout(context, attrs) {
    private var startX = 0f
    private var startY = 0f
    val touchSlop = ViewConfiguration.get(context).scaledTouchSlop //获取阈值


    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x
                startY = ev.y
                return super.onInterceptTouchEvent(ev)
            }

            MotionEvent.ACTION_MOVE -> {
                val dx = ev.x - startX
                val dy = ev.y - startY
                if (abs(dx) > abs(dy) * 0.5 && abs(dx) >= touchSlop) {
                    //水平滑动
                    Log.d("conflict", "dx -> $dx,dy -> $dy")
                    return false
                }
            }

            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                startX = 0f
                startY = 0f
            }
        }
        return super.onInterceptTouchEvent(ev)
    }
}