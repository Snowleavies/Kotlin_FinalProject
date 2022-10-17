package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.database.sqlite.SQLiteDatabase
import android.widget.*

class MainActivity_ticket : AppCompatActivity() {

    private var items:ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ticket)

        var acc = ""
        intent?.extras?.let {
            acc = "${it.getString("acc")}"
        }

        //取得資料庫實體
        dbrw=DBinformation(this).writableDatabase
        //宣告Adapter並連結ListView
        adapter= ArrayAdapter(this,
            android.R.layout.simple_list_item_1,items)
        findViewById<ListView>(R.id.listView).adapter=adapter
        val t0 = findViewById<TextView>(R.id.t0)
        val queryString= "SELECT * FROM information WHERE Acc LIKE '${acc}'"
        val c=dbrw.rawQuery(queryString,null)
        c.moveToFirst()//從第一筆開始輸出
        items.clear()//清空舊資料
        showToast("共有${c.count}筆資料")
        for(i in 0 until c.count){
            //加入新資料
            items.add("會員:${c.getString(1)}\n 交易代碼:${c.getString(0)}\n 電影:${c.getString(2)}\n 總金額:${c.getString(3)}")
            c.moveToNext()//移動到下一筆
        }
        adapter.notifyDataSetChanged()//更新列表資料
        c.close()//關閉Cursor

        findViewById<Button>(R.id.clear).setOnClickListener{
            try {
                //從myTable資料表刪除相同書名的紀錄
                dbrw.execSQL("DELETE FROM information")
            }catch (e: Exception){
                showToast("刪除失敗$e")
            }
        }

        findViewById<Button>(R.id.font).setOnClickListener{
            finish()
        }
    }

    //建立showToast方法顯示Toast訊息
    private fun showToast(text: String)=
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
}