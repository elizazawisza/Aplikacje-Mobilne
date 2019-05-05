package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.add_item.*
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.edit_item.*
import java.util.ArrayList


class AddActivity : AppCompatActivity() {
    val priorityLevels = arrayOf("Small", "Medium", "Big")
    var choosenPriority = String()
    var choosenPhoto = String()

    var choosenIcon = Int
    var alist = ArrayList<String>()
    var testList = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.todolist.R.layout.add_item)
        //val intent = intent
        testList = intent.getParcelableArrayListExtra<Item>("itemList")
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            priorityLevels
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        itemPriority.adapter = adapter
        itemPriority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                choosenPriority = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Code to perform some action when nothing is selected
            }
        }

        books.setOnClickListener {
            println(books)
            books.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "books"
        }
        calendar.setOnClickListener {
            calendar.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "calendar"
        }
        capsules.setOnClickListener {
            capsules.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "capsules"
        }
        cart.setOnClickListener {
            cart.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "cart"
        }
        coding.setOnClickListener {
            coding.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "coding"
        }
        film_reel.setOnClickListener {
            film_reel.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "film_reel"
        }
        friendship.setOnClickListener {
            friendship.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "friendship"
        }
        phone.setOnClickListener {
            phone.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "phone"
        }


    }


    fun clickAdd(view: View){
        println(choosenPriority)
        val newItemName = addName.text
        val newItemDescription = description.text
        val newItemDate = addDate.text

        val itemNew = Item(newItemName.toString(), choosenPriority, newItemDescription.toString(), choosenPhoto, newItemDate.toString())
        testList.add(itemNew)
        val intent = Intent(this, MainActivity::class.java)
        intent.putParcelableArrayListExtra("itemList", testList)
        startActivity(intent)

    }
}
