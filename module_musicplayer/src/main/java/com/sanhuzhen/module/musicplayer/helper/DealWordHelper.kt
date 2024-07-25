package com.sanhuzhen.module.musicplayer.helper

/**
 * @description: 一个处理歌词的工具类
 */
object DealWordHelper {

    /**
     * 使用正则表达式，处理歌词
     * @param word 歌词
     * @return 处理后的歌词
     */
    fun dealWord(word: String): String {
        val processedLyrics = word.lines().map { line ->
            line.replace(Regex("\\[\\d{2}:\\d{2}\\.\\d{1,3}\\]"), "").trim()
        }.filter { it.isNotEmpty() }.joinToString("\n")
        return processedLyrics
    }
}