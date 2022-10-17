package com.example.finalproject

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity_info : AppCompatActivity() {

    private var angle = 0f

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //識別返回對象及執行結果
        if (requestCode == 0 && resultCode == RESULT_OK) {
            //取得影像並顯示於ImageView
            val image = data?.extras?.get("data") ?: return
            findViewById<ImageView>(R.id.img).setImageBitmap(image as Bitmap)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_info)

        var accname = findViewById<TextView>(R.id.accname10)

        intent?.extras?.let {
            accname.text = "${it.getString("account")}"
        }

        findViewById<Button>(R.id.fontpage20).setOnClickListener{
            finish()
        }

        findViewById<Button>(R.id.nextpage10).setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("是否上船內容內容")
                .setNeutralButton("取消"){dialog , which ->}
                .setPositiveButton("確認"){dialog, which ->
                    finish()
                    showToast("上傳成功")
                }.show()
        }

        findViewById<Button>(R.id.photo).setOnClickListener {
            //建立一個要進行影像獲取的Intent物件
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //用 try-catch 避免例外發生，若產生則顯示 Toast
            try {
                startActivityForResult(intent, 0)
            } catch (e:ActivityNotFoundException) {
                Toast.makeText(this, "此裝置無相機應用程式", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.spin).setOnClickListener {
            angle += 90f    //原本角度再加上 90 度
            findViewById<ImageView>(R.id.img).rotation = angle    //使 ImageView 旋轉
        }
    }



    //建立showToast方法顯示Toast訊息
    private fun showToast(text: String)=
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
}