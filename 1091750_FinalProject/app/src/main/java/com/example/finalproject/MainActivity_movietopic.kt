package com.example.finalproject


import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.widget.TextView

class MainActivity_movietopic : AppCompatActivity() {

    private var items:ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_movietopic)

        val accname = findViewById<TextView>(R.id.accname2)
        val nextpage = findViewById<Button>(R.id.nextpage1)
        val fontpage = findViewById<Button>(R.id.fontpage2)
        val tv_movie = findViewById<TextView>(R.id.tv_movie)


        val intent_person = Intent(this, MainActivity_person::class.java)
        var ml = ""
        var md = ""
        var mt = ""

        //取得資料庫實體
        dbrw=DBaccount(this).writableDatabase

        //確認會員
        intent?.extras?.let {
            accname.text = "${it.getString("account")}"
             ml = "${it.getString("ml")}"
             md = "${it.getString("md")}"
             mt = "${it.getString("mt")}"
        }

        //list設定
        setupListView()

        //下一頁
        nextpage.setOnClickListener{

            if (tv_movie.text == "未選擇"){
                showToast("欄位請勿留空")
            }
            else{
                try {
                    //尋找帳號並更新欄位的值

                }catch (e: Exception){
                    showToast("輸入錯誤")
                }

                val b = Bundle()
                b.putString("account", accname.text.toString())
                b.putString("ml", ml)
                b.putString("md", md)
                b.putString("mt", mt)
                b.putString("mop", tv_movie.text.toString())
                intent_person.putExtras(b)
                startActivityForResult(intent_person, 1)
            }

        }
        //上一頁
        fontpage.setOnClickListener{
            finish()
        }
    }

    private var movies = ArrayList<movie>()
    private var selectedmovie = ""
    private lateinit var movielist : ListView
    private lateinit var moviename :TextView


    private fun setupListView() {
        val movieName = arrayOf("媽的多重宇宙", "怪獸與鄧步利多的秘密", "咒", "咒術迴戰0"
            , "費爾的旅程", "奪魂鋸", "奇異博士2", "哥吉拉大戰金剛", "蜘蛛人:無家日", "永恆族")
        val integerArray = resources.obtainTypedArray(R.array.movietopic)

        for (i in 0 until integerArray.length()) {
            val image = integerArray.getResourceId(i, 0)
            val name = movieName[i]
            val movie = movie(image, name)
            movies.add(movie)
        }

        integerArray.recycle()

        moviename = findViewById(R.id.tv_movie)
        movielist = findViewById(R.id.movieListView)
        movielist.setOnItemClickListener { adapterView, view, i, l ->
            selectedmovie = movies[i].name
            moviename.text = selectedmovie.toString()
        }
        movielist.adapter =  mymovielist_adapter(this, R.layout.movieadapter, movies)

    }


    //建立showToast方法顯示Toast訊息
    private fun showToast(text: String)=
        Toast.makeText(this,text,Toast.LENGTH_LONG).show()
}