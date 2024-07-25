package com.sanhuzhen.lib.base.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SongDataHelper (Context: Context,name:String,version:Int):
       SQLiteOpenHelper(Context,name,null,version){
           private val create="create table Book(" +
                   " id integer primary key autoincrement,"+
                   "name text," +
                   "singer text," +
                   "songId text," +
                   "picUrl text)"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(create)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    fun addBook(name: String, singer: String, songId: String, picUrl: String) {
        val db = writableDatabase

        // 创建ContentValues对象，用于存储数据
        val values = ContentValues().apply {
            put("name", name)
            put("singer", singer)
            put("songId", songId)
            put("picUrl", picUrl)
        }

        // 插入数据
        db.insert("Book", null, values)
        db.close() // 关闭数据库连接
    }
    fun deleteBook(id : String){
        val db = writableDatabase
        db.delete("Book","songId=?", arrayOf(id))
        db.close()
    }
}