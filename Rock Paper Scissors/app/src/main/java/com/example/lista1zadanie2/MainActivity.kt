package com.example.lista1zadanie2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random
import android.content.DialogInterface
import android.support.v7.app.AlertDialog


class MainActivity : AppCompatActivity() {

    private var rounds = 0
    private var winRounds = 0
    private var draw = 0
    private var computerWinRounds = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialogWindow()
    }

    fun dialogWindow(){
        val alertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle("About")
        alertDialog.setMessage("This app is a very simple school game named 'rock, paper, scissors'. \nYou play this" +
                " game by clicking on a specific button. \nEnjoy your time!!! ")
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Close",
            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
        alertDialog.show()
    }

    fun turn(player: String, computer:String){
        rounds++

        if(player == "paper" && computer == "rock")
            winRounds++
        else if(player == "scissors" && computer == "paper")
            winRounds++
        else if(player == "rock" && computer == "scissors")
            winRounds++
        else if(player == computer)
            draw++

        computerWinRounds = rounds - winRounds - draw

        findViewById<TextView>(R.id.textView2).text = "Computer won $computerWinRounds of $rounds rounds"
        findViewById<TextView>(R.id.textView).text = "You won $winRounds of $rounds rounds"
    }

    fun computerRound():String {
        val r = Random.nextInt(3-1)+1
        if(r == 1){
            findViewById<ImageView>(R.id.imageView2).setImageResource(R.drawable.paper)
            return "paper"
        }
        else if(r == 2){
            findViewById<ImageView>(R.id.imageView2).setImageResource(R.drawable.stone)
            return "rock"
        }
        else{
            findViewById<ImageView>(R.id.imageView2).setImageResource(R.drawable.scissors)
            return "scissors"
        }
    }

    fun clickPaper(view: View){
        val comp = computerRound()
        findViewById<TextView>(R.id.textView2).text = "$comp"
        findViewById<ImageView>(R.id.imageView).setImageResource(R.drawable.paper)
        turn("paper", comp)
    }

    fun clickScissors(view: View){
        val comp = computerRound()
        findViewById<TextView>(R.id.textView2).text = "$comp"
        findViewById<ImageView>(R.id.imageView).setImageResource(R.drawable.scissors)
        turn("scissors", comp)
    }

    fun clickRock(view: View){
        val comp = computerRound()
        findViewById<TextView>(R.id.textView2).text = "$comp"
        findViewById<ImageView>(R.id.imageView).setImageResource(R.drawable.stone)
        turn("rock", comp)
    }
}
