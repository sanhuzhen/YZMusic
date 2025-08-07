package com.sanhuzhen.module.musicplayer.fragment

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanhuzhen.lib.base.BaseFragment
import com.sanhuzhen.module.musicplayer.adapter.WordAdapter
import com.sanhuzhen.module.musicplayer.databinding.FragmentWordBinding
import com.sanhuzhen.module.musicplayer.helper.DealWordHelper.dealWord
import com.sanhuzhen.module.musicplayer.viewmodel.PlayViewModel

class WordFragment: BaseFragment<FragmentWordBinding>() {

    private val mViewModel by lazy {
        ViewModelProvider(requireActivity())[PlayViewModel::class.java]
    }
    private val rvAdapter: WordAdapter by lazy{WordAdapter()}

    override fun getViewBinding(): FragmentWordBinding {
        return FragmentWordBinding.inflate(layoutInflater)
    }

    override fun afterCreate() {
        initView()
        mViewModel.songLyric.observe(viewLifecycleOwner){lyricText ->
            Log.d("WordFragment", "歌词原始内容：$lyricText")
            val lyricList = dealWord(lyricText)
            Log.d("WordFragment", "解析后歌词行数：${lyricList.size}")
            rvAdapter.submitList(lyricList)
        }
        mViewModel.mCurrentPosition.observe(viewLifecycleOwner){currentMillis->
            updateLyricPosition(currentMillis)
        }
    }
    private fun initView(){
        mBinding.lyricsRecyclerView.apply {
            adapter=rvAdapter
            layoutManager=LinearLayoutManager(context)
        }
    }
    private fun updateLyricPosition(currentMillis: Long) {
        val list = rvAdapter.currentList
        if (list.isEmpty()) return

        // 找到当前播放时间应对应的歌词行索引
        val index = list.indexOfLast { it.timeMillis <= currentMillis }
        if (index == -1) return

        // 如果索引变化，更新 Adapter 并滚动
        if (index != rvAdapter.currentPlayIndex) {
            rvAdapter.updateCurrentPlayingIndex(index)
            // 滚动到中间
            mBinding.lyricsRecyclerView.smoothScrollToPosition(index)
        }
    }

}