package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity_person : AppCompatActivity() {

    private var items:ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase
    private var progresschild  = 0
    private var progressadult = 0
    private var progressold = 0
    private var totalmoney = 0
    private lateinit var childseekbar :SeekBar

    var ml = ""
    var md = ""
    var mt = ""
    var mop = ""

    private lateinit var tv_progress : TextView
    private lateinit var progressbar2 : ProgressBar
    private lateinit var ll_progress : LinearLayout
    private lateinit var nextpage : Button
    private lateinit var fontpage : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_person)



        val accname = findViewById<TextView>(R.id.accname2)
        val font = findViewById<Button>(R.id.fontpage3)
        childseekbar = findViewById<SeekBar>(R.id.childseekbar)
        childseekbar.progress = 0

        //取得資料庫實體
        dbrw=DBaccount(this).writableDatabase

        //確認會員
        intent?.extras?.let {
            accname.text = "${it.getString("account")}"
            ml = "${it.getString("ml")}"
            md = "${it.getString("md")}"
            mt = "${it.getString("mt")}"
            mop = "${it.getString("mop")}"
        }

        childticket()
        adultticket()
        oldticket()

        checkout()

        font.setOnClickListener{
            finish()
        }

    }

    //小孩票數變化
    private fun childticket() {

        val childticket_minus = findViewById<Button>(R.id.childbutton_minus)
        val childticket_plus = findViewById<Button>(R.id.childbutton_plus)
        val childseekbar = findViewById<SeekBar>(R.id.childseekbar)
        val childticket = findViewById<TextView>(R.id.childticket)
        val money = findViewById<TextView>(R.id.money)

        childticket_plus.setOnClickListener {

            if (progresschild == -1) {
                childticket_minus.isEnabled = false
            } else if (progresschild == 9) {
                childticket_plus.isEnabled = false
            } else if (progresschild > -1 && progresschild <= 10) {
                childticket_minus.isEnabled = true
                childticket_plus.isEnabled = true
            }
            progresschild = progresschild + 1
            childseekbar.progress = progresschild * 10
            childticket.text = progresschild.toString() + "  張"
            totalmoney += 160
            money.text = totalmoney.toString() + "  元"
        }

        childticket_minus.setOnClickListener {

            if (progresschild == 1) {
                childticket_minus.isEnabled = false
            } else if (progresschild == 11) {
                childticket_plus.isEnabled = false
            } else if (progresschild > -1 && progresschild <= 10) {
                childticket_minus.isEnabled = true
                childticket_plus.isEnabled = true
            }
            progresschild = progresschild - 1
            childseekbar.progress = progresschild * 10
            childticket.text = progresschild.toString() + "  張"
            totalmoney -= 160
            money.text = totalmoney.toString() + "  元"
        }
    }


    //大人票數變化
    private fun adultticket() {

        val adultticket_minus = findViewById<Button>(R.id.adultbutton_minus)
        val adultticket_plus = findViewById<Button>(R.id.adultbutton_plus)
        val adultseekbar = findViewById<SeekBar>(R.id.adultseekbar)
        val adultticket = findViewById<TextView>(R.id.adulticket)
        val money = findViewById<TextView>(R.id.money)

        adultticket_plus.setOnClickListener {

            if (progressadult == -1) {
                adultticket_minus.isEnabled = false
            } else if (progressadult == 9) {
                adultticket_plus.isEnabled = false
            } else if (progressadult > -1 && progressadult <= 10) {
                adultticket_minus.isEnabled = true
                adultticket_plus.isEnabled = true
            }
            progressadult = progressadult + 1
            adultseekbar.progress = progressadult * 10
            adultticket.text = progressadult.toString() + "  張"
            totalmoney += 200
            money.text = totalmoney.toString() + "  元"
        }

        adultticket_minus.setOnClickListener {

            if (progressadult == 1) {
                adultticket_minus.isEnabled = false
            } else if (progressadult == 11) {
                adultticket_plus.isEnabled = false
            } else if (progressadult > -1 && progressadult <= 10) {
                adultticket_minus.isEnabled = true
                adultticket_plus.isEnabled = true
            }
            progressadult = progressadult - 1
            adultseekbar.progress = progressadult * 10
            adultticket.text = progressadult.toString() + "  張"
            totalmoney -= 200
            money.text = totalmoney.toString() + "  元"
        }
    }

    //老人票數變化
    private fun oldticket() {

        val oldticket_minus = findViewById<Button>(R.id.oldbutton_minus)
        val oldticket_plus = findViewById<Button>(R.id.oldbutton_plus)
        val oldseekbar = findViewById<SeekBar>(R.id.oldseekbar)
        val oldticket = findViewById<TextView>(R.id.oldticket)
        val money = findViewById<TextView>(R.id.money)

        oldticket_plus.setOnClickListener {

            if (progressold == -1) {
                oldticket_minus.isEnabled = false
            } else if (progressold == 9) {
                oldticket_plus.isEnabled = false
            } else if (progressold > -1 && progressold <= 10) {
                oldticket_minus.isEnabled = true
                oldticket_plus.isEnabled = true
            }
            progressold = progressold + 1
            oldseekbar.progress = progressold * 10
            oldticket.text = progressold.toString() + "  張"
            totalmoney += 180
            money.text = totalmoney.toString() + "  元"
        }

        oldticket_minus.setOnClickListener {

            if (progressold == 1) {
                oldticket_minus.isEnabled = false
            } else if (progressold == 11) {
                oldticket_plus.isEnabled = false
            } else if (progressold > -1 && progressold <= 10) {
                oldticket_minus.isEnabled = true
                oldticket_plus.isEnabled = true
            }
            progressold = progressold - 1
            oldseekbar.progress = progressold * 10
            oldticket.text = progressold.toString() + "  張"
            totalmoney -= 180
            money.text = totalmoney.toString() + "  元"
        }
    }

    private fun checkout(){


        val childticket = findViewById<TextView>(R.id.childticket)
        val oldticket = findViewById<TextView>(R.id.oldticket)
        val adultticket = findViewById<TextView>(R.id.adulticket)
        val accname = findViewById<TextView>(R.id.accname2)
        val checkout = Intent(this, MainActivity_checkout::class.java)

        val money = findViewById<TextView>(R.id.money)
        progressbar2 = findViewById(R.id.progressBar2)
        ll_progress = findViewById(R.id.ll_progress)
        tv_progress = findViewById(R.id.tv1_progress)
        nextpage = findViewById(R.id.nextpage2)

        progressbar2.progress = 0
        tv_progress.text = "0%"


        nextpage.setOnClickListener{

            AlertDialog.Builder(this)
                .setTitle("是否確認交易內容")
                .setMessage("確認後無法修改")
                .setNeutralButton("取消"){dialog , which ->}
                .setPositiveButton("確認"){dialog, which ->
                    var progress = 0
                    val b = Bundle()
                    b.putString("ml", ml)
                    b.putString("md", md)
                    b.putString("mt", mt)
                    b.putString("mop", mop)
                    b.putString("ct", childticket.text.toString())
                    b.putString("at", adultticket.text.toString())
                    b.putString("ot", oldticket.text.toString())
                    b.putString("total", money.text.toString())
                    b.putString("account", accname.text.toString())

                    checkout.putExtras(b)
                    if (childticket.text == "0  張" && adultticket.text == "0  張" && oldticket.text == "0  張"){
                        showToast("未填寫正確")
                    }
                    else{
                        ll_progress.visibility = View.VISIBLE
                        GlobalScope.launch(Dispatchers.Main){
                            while (progress < 100){
                                delay(50)
                                progressbar2.progress = progress
                                tv_progress.text = "$progress%"

                                progress++
                            }
                            ll_progress.visibility = View.GONE
                            if(progress >=99)
                                startActivityForResult(checkout, 1)
                        }

                    }
                }.show()



        }



    }


    //建立showToast方法顯示Toast訊息
    private fun showToast(text: String)=
        Toast.makeText(this,text,Toast.LENGTH_LONG).show()
}