package com.sanhuzhen.module.musicplayer.helper

import com.sanhuzhen.module.musicplayer.bean.LyricLine
import com.sanhuzhen.module.musicplayer.bean.SongWordData

/**
 * @description: 一个处理歌词的工具类
 */
object DealWordHelper {

    /**
     * 使用正则表达式，处理歌词
     * @param word 歌词
     * @return 处理后的歌词
     */
    fun dealWord(lrcContent: String): List<LyricLine> {
        val lines = lrcContent.split("\n")
        val lyricLines = mutableListOf<LyricLine>()

        for (line in lines) {
            val trimmedLine = line.trim()
            if (trimmedLine.isEmpty() || !trimmedLine.startsWith("[")) continue

            // 尝试匹配 [mm:ss.xx] 格式
            val timeTextPattern = "\\[(\\d{2}):(\\d{2})\\.(\\d{2})\\]".toRegex()
            val matchResult = timeTextPattern.find(trimmedLine)
            if (matchResult == null) continue

            val (minuteStr, secondStr, milliStr) = matchResult.destructured
            val minutes = minuteStr.toInt()
            val seconds = secondStr.toInt()
            val milliseconds = milliStr.toInt() * 10  // 00 -> 0 ms, 75 -> 750ms? 一般 lrc 是 [xx] 两位，代表 1/100 秒

            val totalMillis = (minutes * 60 + seconds) * 1000L + milliseconds.toLong()

            // 歌词文本是 [] 之后的部分
            val textStartIndex = matchResult.range.last + 1
            val text = if (textStartIndex < trimmedLine.length) {
                trimmedLine.substring(textStartIndex).trim()
            } else {
                ""
            }

            if (text.isNotEmpty()) {
                lyricLines.add(LyricLine(totalMillis, text))
            }
        }

        // 按时间排序
        return lyricLines.sortedBy { it.timeMillis }
    }
}