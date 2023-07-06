package com.koech.pro_sandy


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random
class RegistrationActivity3 : AppCompatActivity() {

    lateinit var guess1: TextView
    lateinit var guess2: EditText
    lateinit var yes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration3)

        guess1 = findViewById(R.id.mGuess1)
        guess2 = findViewById(R.id.mGuess2)
        yes = findViewById(R.id.BtnGuess)

        fun displayUsers(title: String, message: String) {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(title)
            alertDialog.setMessage(message)
            alertDialog.setPositiveButton("You Are A Genius") { dialog, _ ->
                dialog.dismiss()
            }
            alertDialog.create().show()
        }

        yes.setOnClickListener {
            val rangeStart = 1
            val rangeEnd = 10
            val randomNumber = Random.nextInt(rangeStart, rangeEnd + 1)
            val userGuess = guess2.text.toString().toIntOrNull()

            if (userGuess != null) {
                if (userGuess in rangeStart..rangeEnd) {
                    Toast.makeText(this, "Number within range", Toast.LENGTH_SHORT).show()
                    if (userGuess == randomNumber) {
                        displayUsers("Congratulations", "Your guess is correct!")
                    } else {
                        displayUsers("Try Again", "Your guess is incorrect!")
                    }
                } else {
                    Toast.makeText(this, "Number out of range", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(applicationContext,RegistrationActivity4::class.java))
        }
    }
}
