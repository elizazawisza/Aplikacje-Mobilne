package com.example.lista2zadanie1

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random
import android.content.Intent



class PlayActivity : Activity() {

    private var board: ArrayList<Int> = ArrayList()
    private var currentPlayer: Int = 0
    private var gameType: Int = 0
    private var botPlayer = BotPlayerActivity()
    private var botPlayerId: Int = 0
    private var player2turn: String = "Your turn: X"
    private var player1turn: String = "Your turn: O"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val tmpGameType = intent.getStringExtra("type")
        if(tmpGameType == "computer"){
            gameType = 2
        }
        else
            gameType = 1

        prepareGame()
    }

    fun prepareGame(){
        board.clear()
        setContentView(com.example.lista2zadanie1.R.layout.play_activity)
        for(i in 0..24){
            board.add(0)
        }
        val r = Random.nextInt(2)+1
        currentPlayer = r
        if(currentPlayer == 1)
            findViewById<TextView>(R.id.player_turn).text = "$player2turn"
        else
            findViewById<TextView>(R.id.player_turn).text = "$player1turn"
        if(gameType == 2){
            botPlayerId = (currentPlayer%2) + 1
        }


    }

    fun clickField(view: View){
        view.setEnabled(false)
        var buttonId = view.id
        val field = findViewById<Button>(buttonId)
        if(currentPlayer == 1){
            field.text = "X"
            field.setTextColor(Color.parseColor("#FF0000"))
            move(field.tag.toString().toInt(), 1)
            if(checkIfWinner(1))
                displayAlert("Would you like to play again?", "You won!!!")
            else{
                if(checkIfGameOver())
                    displayAlert("Would you like to play again?", "The game is over")
                else
                    nextTurn(1)
            }
        }
        else{
            field.text = "O"
            field.setTextColor(Color.parseColor("#0000FF"))
            move(field.tag.toString().toInt(),2)
            if(checkIfWinner(2))
                displayAlert("Would you like to play again?", "You won!!!")
            else{
                if(checkIfGameOver())
                    displayAlert("Would you like to play again?", "The game is over")
                else
                    nextTurn(2)
            }
        }
    }


    fun nextTurn(currPlayer: Int){
        if(gameType == 1){
            if(currPlayer == 1){
                currentPlayer = 2
                findViewById<TextView>(R.id.player_turn).text = "$player1turn"
            }
            else{
                currentPlayer = 1
                findViewById<TextView>(R.id.player_turn).text = "$player2turn"
            }
        }else{
            botTurn(botPlayerId)
        }
    }

    fun move(fieldId: Int, player: Int){
        board.set(fieldId - 1, player)
    }

    fun checkIfGameOver():Boolean {
        if(!board.contains(0)){
            return true
        }
        return false
    }

    fun displayAlert(alertText: String, alertTitle: String ){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(alertTitle)
        builder.setMessage(alertText)

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext,
                "Yes", Toast.LENGTH_SHORT).show()
            prepareGame()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                "No", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    fun checkIfWinner(player: Int):Boolean {
        for (j in 0 until 25) {
            if (board.elementAt(j) == player) {
                if(checkTmp(player, j))
                    return true
            }
        }
        return false
    }

    fun checkTmp(player: Int, index: Int): Boolean{
        var amount = 0
        if(findCorners(player, index)){
            return true
        }else{
            if(index%5==0){
                for(i in index until index+5)   {
                    if(board.get(i)==player)
                        amount++
                }
                if(amount==5)
                    return true
                if(index == 0){
                    amount = 0
                    for(i in index until 25 step 5){
                        if(board.get(i)==player)
                            amount++
                    }
                    if(amount==5)
                        return true
                }
            }
            else{
                for(i in index until 25 step 5){
                    if(board.get(i)==player)
                        amount++
                }
                println(amount)
                if(amount==5)
                    return true
            }
        }
        return false
    }

    fun findCorners(player: Int, index: Int): Boolean{
        var amount = 0
        if(index==0){
            for(i in index until 25 step 6)   {
                if(board.get(i)==player)
                    amount++
            }
            if(amount==5)
                return true
        }
        else if(index==4){
            for(i in index until 25 step 4)   {
                if(board.get(i)==player)
                    amount++
            }
            if(amount==5)
                return true
        }
        return false
    }

    fun botTurn(player: Int){
        val myFieldName = "button" + botPlayer.turn(board)
        val myFieldID = resources.getIdentifier(myFieldName, "id", "com.example.lista2zadanie1")
        if(player == 2){
            findViewById<TextView>(R.id.player_turn).text = "$player2turn"
            findViewById<Button>(myFieldID).text = "O"
            findViewById<Button>(myFieldID).setTextColor(Color.parseColor("#0000FF"))
            findViewById<Button>(myFieldID).isEnabled = false
        }else{
            findViewById<TextView>(R.id.player_turn).text = "$player1turn"
            findViewById<Button>(myFieldID).text = "X"
            findViewById<Button>(myFieldID).setTextColor(Color.parseColor("#FF0000"))
            findViewById<Button>(myFieldID).isEnabled = false
        }
        move(findViewById<Button>(myFieldID).tag.toString().toInt(), player)
        if(checkIfWinner(player))
            displayAlert("Would you like to play again?", "You won!!!")
        else{
            if(checkIfGameOver())
                displayAlert("Would you like to play again?", "The game is over")
        }



    }

}