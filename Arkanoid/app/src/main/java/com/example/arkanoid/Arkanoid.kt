package com.example.arkanoid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class Arkanoid : AppCompatActivity() {
    private var arkanoidView: ArkanoidView? = null
    private var mode : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (this.intent != null) {
            val extra2 = this.intent.getStringExtra("mode")
            if(extra2 != null){
                mode = extra2
            }
        }
        val screen = windowManager.defaultDisplay
        arkanoidView = ArkanoidView(this, mode, screen)
        setContentView(arkanoidView)
    }
    override fun onResume() {
        super.onResume()
        arkanoidView!!.resume()
    }

    override fun onPause() {
        super.onPause()
        arkanoidView!!.pause()
    }
}