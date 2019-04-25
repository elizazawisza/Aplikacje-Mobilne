package com.example.lista2zadanie1

import android.app.Activity
import kotlin.random.Random

class BotPlayerActivity: Activity()  {

    fun turn(board: ArrayList<Int>) :Int{
        val myTmpBoard: ArrayList<Int> = ArrayList()
        for(i in 0 until board.size ){
            if(board.elementAt(i) == 0){
                myTmpBoard.add(i+1)
            }
        }
        val myRange = myTmpBoard.size
        val r = Random.nextInt(myRange-1)
        return myTmpBoard.elementAt(r)
    }


}