package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity_time : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_time)
        val accname = findViewById<TextView>(R.id.accname)
        val intent_book = Intent(this, MainActivity_Date::class.java)


        //確認會員
        intent?.extras?.let {
                accname.text = "${it.getString("account")}"
        }

        //訂票
        findViewById<Button>(R.id.book).setOnClickListener{
            val b = Bundle()
            b.putString("account", accname.text.toString())
            intent_book.putExtras(b)
            startActivityForResult(intent_book, 1)
        }

        //登出
        findViewById<Button>(R.id.signout).setOnClickListener{
            val main = Intent(this, MainActivity::class.java)

            startActivityForResult(main, 1)
        }

        findViewById<Button>(R.id.history).setOnClickListener{
            val b= Bundle()
            b.putString("acc", accname.text.toString())
            val history = Intent(this, MainActivity_ticket::class.java)
            history.putExtras(b)

            startActivityForResult(history, 1)
        }

        findViewById<Button>(R.id.information).setOnClickListener{
            val b= Bundle()
            b.putString("acc", accname.text.toString())
            val info = Intent(this, MainActivity_info::class.java)
            info.putExtras(b)

            startActivityForResult(info, 1)
        }
    }



}