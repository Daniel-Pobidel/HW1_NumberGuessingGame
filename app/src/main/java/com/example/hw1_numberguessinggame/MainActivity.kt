package com.example.hw1_numberguessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    //some random comment
    var attempts = 0
    var randomNumber = Random().nextInt(100)
    var wonGame = 0
    var totalWins=0
    var totalGuesses=0
    var averageGuesses=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun resetGame() //reset current game stats/hint and generate new number
    {
        hint.text = "Enter a guess to play"
        attemptsBox.text = "Attempts: 0"
        wonGame = 0
        attempts = 0
        randomNumber = Random().nextInt(100)
    }

    fun hardReset() //fully reset game and all stats
    {
        resetGame()
        totalWins = 0
        totalGuesses = 0
        averageGuesses = 0

        averageAttemptsBox.text = "Average Attempts: 0"
        totalAttemptsBox.text = "Total Attempts: 0"
        totalWinsBox.text = "Total Wins: 0"
        Toast.makeText(this, "Game stats reset!", Toast.LENGTH_SHORT).show()
    }

    fun pressButtonMain(view: View){

        if(wonGame == 1) //play again on button press
        {
            Toast.makeText(this, "Game reset!", Toast.LENGTH_SHORT).show()
            resetGame()
        }
        else if(guessBox.text.toString().equals("")) //to prevent crash if user doesn't enter a number
        {
                Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show()
        }
        else if(guessBox.text.toString().toInt() == 444) //reset all stats
        {
            hardReset()
        }
        else
        {
            val guess = guessBox.text.toString().toInt()

            if(guess == randomNumber)
            {
                hint.text = "You Win!! \nNumber was: $randomNumber\nPress Button to Play Again!"
                wonGame++
                totalWins++
                totalWinsBox.text = "Total Wins: $totalWins"
            }
            else if (guess > randomNumber)
            {
                if(guess>100)
                    hint.text = "Hint: Way too high!"
                else
                    hint.text = "Hint: Too high!"
            }
            else
            {
                hint.text = "Hint: Too low!"
            }

            attempts++
            totalGuesses++
            attemptsBox.text = "Attempts: $attempts"
            totalAttemptsBox.text = "Total Attempts: $totalGuesses"

            if(totalWins!=0)
            {
                averageGuesses = totalGuesses/totalWins
            }
            else
            {
                averageGuesses = totalGuesses
            }

            averageAttemptsBox.text = "Average Attempts: $averageGuesses"
        }

    }

}
