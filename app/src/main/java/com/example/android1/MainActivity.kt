package com.example.test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

var nextToPlay: String = "X"
var gameEnded: Boolean = false
var status: String = if (gameEnded) "Winner" else nextToPlay + " To Play"

val buttonIds = listOf(
    R.id.button1, R.id.button2, R.id.button3,
    R.id.button4, R.id.button5, R.id.button6,
    R.id.button7, R.id.button8, R.id.button9
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (btnId in buttonIds) {
            findViewById<Button>(btnId).setOnClickListener {
                onButtonClick(it)
            }
        }
    }

    private fun onButtonClick(view: View?) {
        val button = view as Button

        if (button.text === "") {
            button.text = nextToPlay
            nextToPlay = if (nextToPlay === "X") "O" else "X"

            gameEnded = isGameFinished()

            val gameStatus: TextView = findViewById(R.id.gameStatus)
            gameStatus.text = status
        }
    }
}