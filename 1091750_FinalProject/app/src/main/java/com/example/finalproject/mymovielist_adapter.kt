package com.example.finalproject

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class mymovielist_adapter (
    context: Context,
    private val layout: Int,
    data: ArrayList<movie>
): ArrayAdapter<movie>(context, layout, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = View.inflate(parent.context, layout, null)
        val item = getItem(position) ?: return view

        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textView: TextView = view.findViewById(R.id.textView)

        imageView.setImageResource(item.image)
        textView.text = item.name

        return view
    }
}

data class movie(
    val image: Int,
    val name: String
)