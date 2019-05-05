package com.example.arkanoid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class StartWindow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickMode(view: View){
        val mode = findViewById<Button>(view.id).text
        println(mode)
        when (mode) {
            "Easy" -> easyMode()
            "Medium" -> mediumMode()
            else -> {
                hardMode()
            }
        }
    }

    private fun easyMode(){
        val myintent = Intent(this, Arkanoid::class.java)
        myintent.putExtra("mode", "easy")
        startActivity(myintent)
    }
    private fun hardMode(){
        val myintent = Intent(this, Arkanoid::class.java)
        myintent.putExtra("mode", "hard")
        startActivity(myintent)
    }
    private fun mediumMode(){
        val myintent = Intent(this, Arkanoid::class.java)
        myintent.putExtra("mode", "medium")
        startActivity(myintent)
    }


}