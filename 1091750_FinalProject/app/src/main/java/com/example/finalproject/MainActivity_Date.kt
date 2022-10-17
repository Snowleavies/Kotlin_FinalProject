package com.example.finalproject

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.NestedScrollingParent
import java.text.FieldPosition

class MainActivity_Date : AppCompatActivity() {

    private var items:ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_date)

        var item = arrayOf("台北元智影城", "桃園元智影城", "台中元智影城", "新竹元智影城", "板橋元智影城",)
        val adapter1 = ArrayAdapter.createFromResource(this, R.array.moviedate, android.R.layout.simple_list_item_1)
        val adapter2 = ArrayAdapter.createFromResource(this, R.array.movietime, android.R.layout.simple_list_item_1)
        val accname = findViewById<TextView>(R.id.accname1)
        val movielocation_button = findViewById<Button>(R.id.movieloaction_button)
        val movielocation = findViewById<TextView>(R.id.movieloaction)
        val moviedate = findViewById<TextView>(R.id.moviedate)
        val movietime = findViewById<TextView>(R.id.movietime)
        val nextpage = findViewById<Button>(R.id.nextpage)
        val fontpage = findViewById<Button>(R.id.fontpage1)
        val sp1 = findViewById<Spinner>(R.id.sp)
        val sp2 = findViewById<Spinner>(R.id.sp1)
        var num = 0
        var num1 = 0

        val ed1 = findViewById<EditText>(R.id.ed1)
        ed1.setText(movielocation.text)

        val ed2 = findViewById<EditText>(R.id.ed2)
        ed2.setText(accname.text)

        val intent_movie = Intent(this, MainActivity_movietopic::class.java)

        //取得資料庫實體
        dbrw=DBaccount(this).writableDatabase

        //宣告Adapter並連結ListView
        adapter= ArrayAdapter(this,
            android.R.layout.simple_list_item_1,items)

        //確認會員
        intent?.extras?.let {
            accname.text = "${it.getString("account")}"
        }

        //顯示影院地點
        movielocation_button.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("選擇影院")
                .setItems(item) {dialogInterface, i ->
                    movielocation.text = "${item[i]}"
                }.show()
        }

        //顯示日期
        sp1.adapter = adapter1
        sp1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
               val select = parent?.getItemIdAtPosition(position)
                val s = sp1.selectedItem.toString()

                if(num1 == 0)
                    moviedate.text = "未選擇"
                else
                    moviedate.text = s

                num1 = num1 + 1
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                moviedate.text = ""
            }
        }

        //顯示時間
        sp2.adapter = adapter2
        sp2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val select = parent?.getItemIdAtPosition(position)
                val sp_item = sp2.selectedItem.toString()

                if(num == 0)
                    movietime.text = ""
                else
                    movietime.text = sp_item

                num = num + 1
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                movietime.text = ""
            }
        }

        //下一頁
        nextpage.setOnClickListener{
            //判斷是否有填入帳號或密碼
            if (movielocation.length()<4||moviedate.length()<4||movietime.length()<1)
                showToast("欄位請勿留空")
            else{
                try {
                    //尋找帳號並更新欄位的值

                }catch (e: Exception){
                    showToast("輸入錯誤")
                }

                val b = Bundle()
                b.putString("account", accname.text.toString())
                b.putString("ml", movielocation.text.toString())
                b.putString("md", moviedate.text.toString())
                b.putString("mt", movietime.text.toString())
                intent_movie.putExtras(b)
                startActivityForResult(intent_movie, 1)
            }



        }

        //上一頁
        fontpage.setOnClickListener{
            finish()
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