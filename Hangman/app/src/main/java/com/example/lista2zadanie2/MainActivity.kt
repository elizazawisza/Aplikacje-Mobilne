package com.example.lista2zadanie2

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random



private var images: ArrayList<Int> = ArrayList()
private var numIncorr: Int = 0
private var numCorr: Int = 0
private var lookingWord: String = ""
private var currentWord: String = ""



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPhotoArray()
        playGame()
    }

    fun clickLetter(view: View){
        var buttonId = view.id
        val clickedLetter = findViewById<Button>(buttonId).text
        view.setEnabled(false)
        if(lookingWord.contains(clickedLetter)){
            for (i in 0..lookingWord.length-1){
                if(lookingWord.elementAt(i).toString()==clickedLetter.toString()){
                    setGuessingStep(clickedLetter, i)
                    numCorr++
                }
            }
            checkIfWinner()
        }
        else{
            setNewImage(numIncorr)
            numIncorr++
            checkIfLoser()
        }
    }

    fun playGame(){
        val yourVar = resources.getStringArray(R.array.words_list)
        val sizeOfWord = yourVar.size
        val r = Random.nextInt(sizeOfWord)
        lookingWord =  yourVar[r]
        setGuessingStep1('_', lookingWord.length-1)
    }

    fun newGame(){
        currentWord = ""
        numIncorr = 0
        numCorr = 0
        setContentView(R.layout.activity_main)
        playGame()
    }

    fun setNewImage(number: Int){
        val currImage = images.get(number)
        findViewById<ImageView>(R.id.imageView).setImageResource(currImage)
    }

    fun setGuessingStep1(letter: Char, size: Int){
        currentWord = ""
        for(i in 0..size){
            currentWord+=letter
        }
        findViewById<TextView>(R.id.word).text = "$currentWord"
    }

    fun setGuessingStep(letter: CharSequence, position: Int){
        val newWord = currentWord.replaceRange(position..position,  letter)
        currentWord = newWord
        findViewById<TextView>(R.id.word).text = "$currentWord"
    }

    fun setPhotoArray(){
        images.add(R.drawable.hangman1)
        images.add(R.drawable.hangman2)
        images.add(R.drawable.hangman3)
        images.add(R.drawable.hangman4)
        images.add(R.drawable.hangman5)
        images.add(R.drawable.hangman6)
        images.add(R.drawable.hangman7)
        images.add(R.drawable.hangman8)
        images.add(R.drawable.hangman9)
        images.add(R.drawable.hangman10)
        images.add(R.drawable.hangman11)
        images.add(R.drawable.hangman12)
    }

    fun checkIfWinner(){
        if(numCorr == lookingWord.length){
            displayAlert("Would you like to play again?", "Congratulations!!! \nYou quess the word")
        }
    }

    fun checkIfLoser(){
        if(numIncorr == 12){
            displayAlert("You didn't quess the world: " + lookingWord + "\nWould you like to play again?", "Game over")
        }
    }

    fun displayAlert(alertText: String, alertTitle: String ){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(alertTitle)
        builder.setMessage(alertText)

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext,
                "Yes", Toast.LENGTH_SHORT).show()
            newGame()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                "No", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}

