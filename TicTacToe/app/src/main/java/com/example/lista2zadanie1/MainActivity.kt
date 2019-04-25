package com.example.lista2zadanie1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun clickPlayer(view: View){
        val intent = Intent(this, PlayActivity::class.java)
        intent.putExtra("type", "player")
        startActivity(intent)
    }

    fun clickComputer(view: View){
        val intent = Intent(this, PlayActivity::class.java)
        intent.putExtra("type", "computer")
        startActivity(intent)
    }


}
