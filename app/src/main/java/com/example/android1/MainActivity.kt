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
        findViewById<Button>(R.id.resetButton).setOnClickListener{
            onResetClick()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun onButtonClick(view: View?) {
        val button = view as Button

        if (button.text === "") {
            button.text = nextToPlay
            nextToPlay = if (nextToPlay === "X") "O" else "X"

            val gameStatus: TextView = findViewById(R.id.gameStatus)
            status = "$nextToPlay To Play"
            gameStatus.text = status

            val resetButton: Button = findViewById(R.id.resetButton)
            resetButton.visibility = if (gameEnded) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun onResetClick(){
        for (btnId in buttonIds) {
            findViewById<Button>(btnId).text = ""
        }
        nextToPlay="X"
        gameEnded = false
        status = "$nextToPlay To Play"
        val gameStatus: TextView = findViewById(R.id.gameStatus)
        gameStatus.text = status
        val resetButton: Button = findViewById(R.id.resetButton)
        resetButton.visibility = View.INVISIBLE
        }
    }