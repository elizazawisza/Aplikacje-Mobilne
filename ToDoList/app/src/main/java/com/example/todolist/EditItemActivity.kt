package com.example.todolist

import android.R
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.add_item.*
import kotlinx.android.synthetic.main.edit_item.*
import kotlinx.android.synthetic.main.show_item.*
import java.util.ArrayList

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class EditItemActivity : AppCompatActivity() {
    val priorityLevels = arrayOf("Small", "Medium", "Big")
    var choosenPriority = String()
    var choosenPhoto = String()
    var choosenName = String()
    var choosenDescription = String()
    var choosenDate = String()

    var index = 0
    var testList = ArrayList<Item>()

    public override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("tasks", testList)
        outState.putInt("index", index)
        outState.putString("priority", choosenPriority)
        outState.putString("photo", choosenPhoto)
        outState.putString("name", choosenName )
        outState.putString("description", choosenDescription)
        outState.putString("date", choosenDate)
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.todolist.R.layout.edit_item)
        testList = intent.getParcelableArrayListExtra<Item>("itemList")
        index = intent.getIntExtra("index",0)

        if (savedInstanceState != null) {
            testList = savedInstanceState.getParcelableArrayList("tasks")
            index = savedInstanceState.getInt("index")
            choosenPhoto = savedInstanceState.getString("photo")
            choosenPriority = savedInstanceState.getString("priority")
            choosenName = savedInstanceState.getString("name")
            choosenDescription = savedInstanceState.getString("description")
            choosenDate = savedInstanceState.getString("date")
        }


        val myItem = testList[index]

        val adapter = ArrayAdapter(
            this,
            R.layout.simple_spinner_item,
            priorityLevels
        )
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        editItemPriority.adapter = adapter
        chosePhoto()
        editItemPriority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                choosenPriority = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        editName.setText(myItem.name)
        editDate.setText(myItem.date)
        editDescription.setText(myItem.descrption)

        when (myItem.priority) {
            "Big" -> {
                editItemPriority.setSelection(2)
            }
            "Medium" -> {
                editItemPriority.setSelection(1)
            }
            "Small" -> {
                editItemPriority.setSelection(0)
            }
        }

        when (myItem.icon) {
            "books" -> {
                previousPhoto.setImageResource(com.example.todolist.R.drawable.books)
            }
            "calendar" -> {
                previousPhoto.setImageResource(com.example.todolist.R.drawable.calendar)
            }
            "capsules" -> {
                previousPhoto.setImageResource(com.example.todolist.R.drawable.capsules)
            }
            "cart" -> {
                previousPhoto.setImageResource(com.example.todolist.R.drawable.cart)
            }
            "coding" -> {
                previousPhoto.setImageResource(com.example.todolist.R.drawable.coding)
            }
            "film_reel" -> {
                previousPhoto.setImageResource(com.example.todolist.R.drawable.film_reel)
            }
            "friendship" -> {
                previousPhoto.setImageResource(com.example.todolist.R.drawable.friendship)
            }
            "phone" -> {
                previousPhoto.setImageResource(com.example.todolist.R.drawable.phone)
            }
        }

    }

    fun chosePhoto(){
        editBooks.setOnClickListener {
            println(books)
            editBooks.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "books"
        }
        editCalendar.setOnClickListener {
            editCalendar.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "calendar"
        }
        editCapsules.setOnClickListener {
            editCapsules.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "capsules"
        }
        editCart.setOnClickListener {
            editCart.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "cart"
        }
        editCoding.setOnClickListener {
            editCoding.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "coding"
        }
        editFilm_reel.setOnClickListener {
            editFilm_reel.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "film_reel"
        }
        editFriendship.setOnClickListener {
            editFriendship.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "friendship"
        }
        editPhone.setOnClickListener {
            editPhone.setBackgroundColor(Color.parseColor("#ccf2ff"))
            choosenPhoto = "phone"
        }

    }


    fun clickSave(view: View){
        println(choosenPriority)
        println(editDate.text)
        val newItemName = editName.text
        val newItemDescription = editDescription.text
        val newItemDate = editDate.text
        val itemNew = Item(newItemName.toString(), choosenPriority, newItemDescription.toString(), choosenPhoto, newItemDate.toString())
        println(itemNew)
        println(index)
        testList[index] = itemNew
        val intent = Intent(this, MainActivity::class.java)
        intent.putParcelableArrayListExtra("itemList", testList)
        startActivity(intent)

    }
}
