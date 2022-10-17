package com.example.finalproject

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class SecActivity : AppCompatActivity() {
    private var items:ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)
        //取得資料庫實體
        dbrw=DBaccount(this).writableDatabase
        //宣告Adapter並連結ListView
        adapter= ArrayAdapter(this,
            android.R.layout.simple_list_item_1,items)
        //設定監聽器
        setListener()
        fontpage()
    }

    override fun onDestroy() {
        dbrw.close()//關閉資料庫
        super.onDestroy()
    }

    private fun setListener(){
        val account = findViewById<EditText>(R.id.acctext1)
        val password = findViewById<EditText>(R.id.pwdtext1)
        val check = findViewById<EditText>(R.id.comfirmtext)
        findViewById<Button>(R.id.signin_sec).setOnClickListener{
            //判斷是否有填入帳號或密碼
            if (account.length()<1||password.length()<1||check.length()<1)
                showToast("欄位請勿留空")
            else
            {
                if(password.text.toString() == check.text.toString())
                    try {
                        //新增一筆書籍紀錄於myTable資料表
                        dbrw.execSQL(
                            "INSERT INTO myTable(Account, password) VALUES(?, ?)",
                            arrayOf(account.text.toString(),
                                password.text.toString())
                        )
                        showToast("註冊成功")
                        setResult(Activity.RESULT_OK, Intent())
                        finish()
                    }catch (e: Exception){
                        showToast("帳號相同")
                    }
                else
                    showToast("確認密碼錯誤")

            }

        }
    }

    private fun fontpage(){
        findViewById<Button>(R.id.fontpage).setOnClickListener{
            finish()
        }
    }
    //建立showToast方法顯示Toast訊息
    private fun showToast(text: String)=
        Toast.makeText(this,text,Toast.LENGTH_LONG).show()

}