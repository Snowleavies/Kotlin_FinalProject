package com.example.finalproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBinformation (
    context: Context,
    name: String = database1,
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = v
) : SQLiteOpenHelper(context, name, factory, version) {
    companion object{
        private const val database1 = "infoDatabase"   //資料庫名稱
        private const val v = 1     //資料庫版本
    }

    override fun onCreate(dBinformation: SQLiteDatabase) {
        //建立 myTable 資料表，表內有 book 字串欄位和 price 整數欄位
        dBinformation.execSQL("CREATE TABLE information(code text Primary KEY, Acc text NOT NULL, movie text NOT NULL, money text not null)")
    }

    override fun onUpgrade(dBinformation: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {
        dBinformation.execSQL("DROP TABLE IF EXISTS information")
        onCreate(dBinformation)
    }
}