package com.example.finalproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBaccount (
    context: Context,
    name: String = database,
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = v
) : SQLiteOpenHelper(context, name, factory, version) {
    companion object {
        private const val database = "myAccDatabase"   //資料庫名稱
        private const val v = 1     //資料庫版本
    }

    override fun onCreate(db: SQLiteDatabase) {
        //建立 myTable 資料表，表內有 book 字串欄位和 price 整數欄位
        db.execSQL("CREATE TABLE myTable(Account text PRIMARY KEY, password text NOT NULL)")
    }

    override fun onUpgrade(
        db: SQLiteDatabase, oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS myTable")
        onCreate(db)
    }
}