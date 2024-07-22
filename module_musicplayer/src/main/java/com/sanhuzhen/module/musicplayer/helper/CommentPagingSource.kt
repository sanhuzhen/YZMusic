package com.sanhuzhen.module.musicplayer.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sanhuzhen.module.musicplayer.api.ApiService
import com.sanhuzhen.module.musicplayer.bean.Comment

/**
 * @description: 对Paging3的数据进行封装
 */
class CommentPagingSource(
    private val apiService: ApiService, private val type: String,
    private val id: String,
    private val sortType: String
) : PagingSource<Int, Comment>() {
    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize

            // 调用 API 获取数据
            val response = apiService.getSongComment(
                type = type,
                id = id,
                sortType = sortType,
                cursor = "1602072870260",
                pageSize = pageSize,
                pageNo = page
            )

            LoadResult.Page(
                data = response.data.comments,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.data.comments.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}