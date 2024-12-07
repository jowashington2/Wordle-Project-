package com.example.wordle

import FourLetterWordList
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var G1: TextView
    private lateinit var G1C: TextView
    private lateinit var G2: TextView
    private lateinit var G2C: TextView
    private lateinit var G3: TextView
    private lateinit var G3C: TextView
    private lateinit var correctWordTextView: TextView
    private lateinit var guessEntry: EditText
    private lateinit var guessButton: Button

    private var targetWord: String = FourLetterWordList.getRandomFourLetterWord()
    private var guessCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        G1 = findViewById(R.id.G1)
        G1C = findViewById(R.id.G1C)
        G2 = findViewById(R.id.G2)
        G2C = findViewById(R.id.G2C)
        G3 = findViewById(R.id.G3)
        G3C = findViewById(R.id.G3C)
        correctWordTextView = findViewById(R.id.correctword)
        guessEntry = findViewById(R.id.guessEntry)
        guessButton = findViewById(R.id.button2)

        correctWordTextView.visibility = View.GONE

        guessButton.setOnClickListener {
            handleGuess()
        }
    }

    private fun handleGuess() {
        val guess = guessEntry.text.toString().uppercase()


        if (guess.length != 4) {
            guessEntry.error = "Guess must be 4 letters!"
            return
        }

        when (guessCount) {
            0 -> {
                G1.text = guess
                G1C.text = checkGuess(guess)
            }
            1 -> {
                G2.text = guess
                G2C.text = checkGuess(guess)
            }
            2 -> {
                G3.text = guess
                G3C.text = checkGuess(guess)
                correctWordTextView.text = "The correct word was: $targetWord"
                correctWordTextView.visibility = View.VISIBLE
                guessButton.isEnabled = false
            }
        }

        guessCount++
        guessEntry.text.clear()
    }

    private fun checkGuess(guess: String): String {
        var correctCount = 0
        var inPositionCount = 0

        for (i in guess.indices) {
            if (guess[i] == targetWord[i]) {
                inPositionCount++
            } else if (targetWord.contains(guess[i])) {
                correctCount++
            }
        }

        return "In position: $inPositionCount, Correct letters: $correctCount"
    }
}
