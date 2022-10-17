package com.example.finalproject

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.database.sqlite.SQLiteDatabase
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity_checkout : AppCompatActivity() {

    private var items:ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_checkout)


        dbrw=DBinformation(this).writableDatabase

        val accname = findViewById<TextView>(R.id.accname3)
        val accc = findViewById<TextView>(R.id.accc)
        val thee = findViewById<TextView>(R.id.thee)

        val movietopic = findViewById<TextView>(R.id.movietopic)
        val movielocation = findViewById<TextView>(R.id.moviel)
        val moviedate = findViewById<TextView>(R.id.movied)
        val movietime = findViewById<TextView>(R.id.moviet)
        val childticket = findViewById<TextView>(R.id.childt)
        val adultticket = findViewById<TextView>(R.id.adultt)
        val oldticket = findViewById<TextView>(R.id.oldt)
        val totalm = findViewById<TextView>(R.id.totalm)
        val acc = findViewById<TextView>(R.id.accc)
        val code = findViewById<TextView>(R.id.thee)
        val finish = findViewById<Button>(R.id.nextpage4)

        val b = Bundle()
        val intent_sec = Intent(this, MainActivity_time::class.java)

        var ml = ""
        var md = ""
        var mt = ""
        var mop = ""
        var at = ""
        var ct = ""
        var ot = ""
        var total = ""

        //確認會員
        intent?.extras?.let {
            accname.text = "${it.getString("account")}"
            total = "${it.getString("total")}"
            ml = "${it.getString("ml")}"
            md = "${it.getString("md")}"
            mt = "${it.getString("mt")}"
            mop = "${it.getString("mop")}"
            at = "${it.getString("at")}"
            ct = "${it.getString("ct")}"
            ot = "${it.getString("ot")}"
        }
        val m = (Math.random() * 3452456).toInt()
        movietopic.text = mop.toString()
        moviedate.text = md
        movielocation.text =ml
        movietime.text = mt
        totalm.text = total
        childticket.text = ct
        adultticket.text = at
        oldticket.text = ot
        acc.text = accname.text
        thee.text = "YZU" + m.toString()

        finish.setOnClickListener{
            try {
                val cv = ContentValues()
                cv.put("code", "${thee.text}")
                cv.put("Acc", "${accname.text}")
                cv.put("movie", "${movietopic.text}")
                cv.put("money", "${totalm.text}")
                dbrw.insert("information", null, cv)

                b.putString("account", accname.text.toString())
                intent_sec.putExtras(b)
                startActivityForResult(intent_sec, 1)

            }catch (e: Exception){
                showToast("錯誤")
            }
        }
    }
    override fun onDestroy() {
        dbrw.close()//關閉資料庫
        super.onDestroy()
    }


    //建立showToast方法顯示Toast訊息
    private fun showToast(text: String)=
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
}