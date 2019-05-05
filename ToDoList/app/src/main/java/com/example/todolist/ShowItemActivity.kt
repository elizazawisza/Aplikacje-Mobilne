package com.example.todolist

import android.R
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.add_item.*
import kotlinx.android.synthetic.main.show_item.*
import java.util.ArrayList

class ShowItemActivity : AppCompatActivity() {
    var testList = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.todolist.R.layout.show_item)
        val intent = intent
        testList = intent.getParcelableArrayListExtra<Item>("itemList")
        val position = intent.getIntExtra("index",0)

        val myItem = testList[position]
        ItemNameShow.text = myItem.name
        ItemPriorityShow.text = myItem.priority
        ItemDateShow.text = myItem.date
        ItemDescriptionShow.text = myItem.descrption

        when (myItem.icon) {
            "books" -> {
                ItemIconShow.setImageResource(com.example.todolist.R.drawable.books)
            }
            "calendar" -> {
                ItemIconShow.setImageResource(com.example.todolist.R.drawable.calendar)
            }
            "capsules" -> {
                ItemIconShow.setImageResource(com.example.todolist.R.drawable.capsules)
            }
            "cart" -> {
                ItemIconShow.setImageResource(com.example.todolist.R.drawable.cart)
            }
            "coding" -> {
                ItemIconShow.setImageResource(com.example.todolist.R.drawable.coding)
            }
            "film_reel" -> {
                ItemIconShow.setImageResource(com.example.todolist.R.drawable.film_reel)
            }
            "friendship" -> {
                ItemIconShow.setImageResource(com.example.todolist.R.drawable.friendship)
            }
            "phone" -> {
                ItemIconShow.setImageResource(com.example.todolist.R.drawable.phone)
            }
        }

    }


    fun clickBack(view: View){
        finish()
    }
}
