package com.example.todolist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.example.todolist.R
import java.io.File
import java.io.PrintWriter









class MainActivity : AppCompatActivity() {

    var testList = ArrayList<Item>()

    public override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("tasks", testList)
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        try {
            testList = intent.getParcelableArrayListExtra<Item>("itemList")
        } catch (e: Exception) {
        }
        if (savedInstanceState != null) {
            testList = savedInstanceState.getParcelableArrayList("tasks")
        }
        val myadapter = MyArrayAdapter(this, testList)

        val fab = findViewById<FloatingActionButton>(R.id.quickAddButton)
        fab.setOnClickListener {
            addNewItem("quick")
            myadapter.notifyDataSetChanged()
        }



        itemsList.adapter = myadapter

        itemsList.setOnItemClickListener { _,_, index, _ ->
            val intent = Intent(this, ShowItemActivity::class.java)
            intent.putParcelableArrayListExtra("itemList", testList)
            intent.putExtra("index",index)
            startActivity(intent)
        }
        itemsList.setOnItemLongClickListener { _,_, index, _ ->
            val intent = Intent(this, EditItemActivity::class.java)
            intent.putParcelableArrayListExtra("itemList", testList)
            intent.putExtra("index",index)
            startActivity(intent)
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val myadapter = MyArrayAdapter(this, testList)
        itemsList.adapter = myadapter
        when (item.itemId) {
            R.id.add -> {
                val intent = Intent(this, AddActivity::class.java)
                intent.putParcelableArrayListExtra("itemList", testList)
                startActivity(intent)
            }
            R.id.pDesc -> {
            var sortedList = testList.sortedWith(compareBy({ it.priority }))
            var i=0
            for (obj in sortedList) {
                testList[i] = obj
                i++
            }
            myadapter.notifyDataSetChanged()
            }
            R.id.pAsc -> {
                var sortedList = testList.sortedWith(compareBy({ it.priority }))
                var i=testList.size-1
                for (obj in sortedList) {
                    testList[i] = obj
                    i--
                }
                myadapter.notifyDataSetChanged()
            }
            R.id.nAsc -> {
                var sortedList = testList.sortedWith(compareBy({ it.name }))
                var i=0
                for (obj in sortedList) {
                    testList[i] = obj
                    i++
                }
                myadapter.notifyDataSetChanged()
            }
            R.id.nDesc -> {
                var sortedList = testList.sortedWith(compareBy({ it.name }))
                var i=testList.size-1
                for (obj in sortedList) {
                    testList[i] = obj
                    i--
                }
                myadapter.notifyDataSetChanged()
            }
        }
        return when (item.itemId) {
            R.id.pDesc -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun addNewItem(type: String){
        if (type == "quick"){
            val newItem = quickAdd.text
            val itemNew = Item(newItem.toString(), "Small", "", "", "10-04-2019")
            testList.add(itemNew)
            quickAdd.text = null
        }
    }
}
