package com.user.guessnumber

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var textViewHint: TextView
    private lateinit var buttonGuess: Button

    private var secretNumber = 0
    private var attempts = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        textViewHint = findViewById(R.id.textViewHint)
        buttonGuess = findViewById(R.id.buttonGuess)

        // Generate a random number between 1 and 10
        secretNumber = Random.nextInt(1, 10)

        buttonGuess.setOnClickListener {
            val guess = editTextNumber.text.toString().toIntOrNull()
            if (guess != null) {
                attempts++
                if (guess == secretNumber) {
                    showSuccessDialog()
                } else {
                    val message = if (guess < secretNumber) {
                        "Try a higher number"
                    } else {
                        "Try a lower number"
                    }
                    textViewHint.text = message
                }
            } else {
                textViewHint.text = "Please enter a valid number"
            }
        }
    }

    private fun showSuccessDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Congratulations!")
            .setMessage("You guessed the number $secretNumber in $attempts attempts.")
            .setPositiveButton("Play Again") { _, _ ->
                resetGame()
            }
            .setNegativeButton("Exit") { _, _ ->
                finish()
            }
            .setCancelable(false)
            .create()
        dialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun resetGame() {
        secretNumber = Random.nextInt(1, 10)
        attempts = 0
        editTextNumber.text.clear()
        textViewHint.text = "Guess a number between 1 and 10"
    }
}