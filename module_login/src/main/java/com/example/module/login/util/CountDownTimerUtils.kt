package com.example.module.login.util

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.CountDownTimer
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.sanhuzhen.module.login.R

class CountDownTimeUtils(textView: TextView, millisInFuture: Long,
                             countDownInterval: Long
    ) : CountDownTimer(millisInFuture, countDownInterval) {
        private var mTextView: TextView = textView
        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {
            mTextView.isClickable = false
            mTextView.text = "${millisUntilFinished/1000}"+"s后重新获取"
            mTextView.setBackgroundColor(mTextView.context.resources.getColor(R.color.grey,null))
            val spannableString= SpannableString(mTextView.text.toString())
            val span= ForegroundColorSpan(Color.RED)
            spannableString.setSpan(span,0,2, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            mTextView.text=spannableString
        }

        override fun onFinish() {
            mTextView.text = "获取验证码"
            mTextView.isClickable = true
            mTextView.setBackgroundColor(mTextView.context.resources.getColor(R.color.grey,null))
        }

    }