package com.example.android1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

var nextToPlay: String = "X"
var gameEnded: Boolean = false
var status: String = if (gameEnded) "Winner" else "$nextToPlay To Play"

val buttonIds = listOf(
    R.id.button1, R.id.button2, R.id.button3,
    R.id.button4, R.id.button5, R.id.button6,
    R.id.button7, R.id.button8, R.id.button9
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val gameStatus: TextView = findViewById(R.id.gameStatus)
        gameStatus.text = "$nextToPlay To Play"

        for (btnId in buttonIds) {
            findViewById<Button>(btnId).setOnClickListener {
                onButtonClick(it)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun onButtonClick(view: View?) {
        val button = view as Button
        if (button.text.isEmpty() && !gameEnded) {
            val gameStatus: TextView = findViewById(R.id.gameStatus)
            button.text = nextToPlay
            nextToPlay = if (nextToPlay == "X") "O" else "X"
            gameStatus.text = "$nextToPlay To Play"
            gameEnded = isGameFinished()
            if(gameEnded) {
                gameStatus.text = status
            }

        }
    }

    private fun isGameFinished() : Boolean{
        val board = buttonIds.map { id ->
            findViewById<Button>(id).text.toString()
        }

        val winningCombinations = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )

        for (combination in winningCombinations) {
            val (a, b, c) = combination
            if (board[a].isNotEmpty() && board[a] == board[b] && board[a] == board[c]) {
                status = "Winner: ${board[a]}"
                return true
                }
            }
        return false
    }
}