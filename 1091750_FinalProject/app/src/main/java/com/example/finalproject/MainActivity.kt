package com.example.finalproject

import android.database.sqlite.SQLiteDatabase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var items:ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signin = findViewById<Button>(R.id.signin)
        val login = findViewById<Button>(R.id.login)
        val acc = findViewById<EditText>(R.id.acctext)
        val pwd = findViewById<EditText>(R.id.pwdtext)
        val clearr = findViewById<Button>(R.id.clearr)
        val intent_sec = Intent(this, MainActivity_time::class.java)


        acc.setText("")
        pwd.setText("")

        //取得資料庫實體
        dbrw=DBaccount(this).writableDatabase
        //切換註冊頁面
        signin.setOnClickListener{
            acc.setText("")
            pwd.setText("")
            val intent = Intent(this, SecActivity::class.java)
            startActivityForResult(intent, 1)
        }
        //登入
        login.setOnClickListener{
            val DB = "SELECT * FROM myTable"
            val c = dbrw.rawQuery(DB, null)
            c.moveToFirst()//從第一筆開始輸出
            items.clear()//清空舊資料
            if(c.count == 0)
                showToast("請先註冊帳號")
            else{
                for(i in 0 until c.count){
                    //確認帳戶
                    val b = Bundle()
                    val checkacc = c.getString(0)
                    val checkpwd = c.getString(1)

                    if (acc.text.toString() == checkacc && pwd.text.toString() == checkpwd) {
                        b.putString("account", acc.text.toString())
                        intent_sec.putExtras(b)
                        showToast("登入")
                        startActivityForResult(intent_sec, 1)
                        break
                    }
                    else if(acc.text.toString() == checkacc && pwd.text.toString() != checkpwd){
                        showToast("帳號密碼錯誤")
                    }
                    //移動到下一筆
                    c.moveToNext()
                }

            }
        }

        clearr.setOnClickListener{
            try {
                //從myTable資料表刪除相同書名的紀錄
                dbrw.execSQL("DELETE FROM myTable")
            }catch (e: Exception){
                showToast("刪除失敗$e")
            }
        }
    }
    override fun onDestroy() {
        dbrw.close()//關閉資料庫
        super.onDestroy()
    }

    //建立showToast方法顯示Toast訊息
    private fun showToast(text: String)=
        Toast.makeText(this,text,Toast.LENGTH_LONG).show()





}