package com.koech.pro_sandy

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity2 : AppCompatActivity() {
    lateinit var edtName1: EditText
    lateinit var edtEmail: EditText
    lateinit var edtPhone: EditText
    lateinit var edtAge: EditText
    lateinit var btnNext: Button
    lateinit var progress: ProgressDialog
    lateinit var mAuth: FirebaseAuth


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration2)


        val daySpinner: Spinner = findViewById(R.id.daySpinner)
        val monthSpinner: Spinner = findViewById(R.id.monthSpinner)
        val yearSpinner: Spinner = findViewById(R.id.yearSpinner)
        val days = (1..31).toList()
        val dayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, days)
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        daySpinner.adapter = dayAdapter



        val months = arrayOf(
            "MONTH","January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"
        )
        val monthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        monthSpinner.adapter = monthAdapter



        val years = (1950..2023).toList()


        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearSpinner.adapter = yearAdapter



        edtName1 = findViewById(R.id.mEDTNAME1)
        edtEmail = findViewById(R.id.mEDTEMAIL)
        edtPhone = findViewById(R.id.mEDTNUMBER)
        edtAge = findViewById(R.id.mEDTNUMBER1)
        btnNext = findViewById(R.id.mbtnproceed)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")

        btnNext.setOnClickListener {

            var email = edtEmail.text.toString().trim()
            var name1 = edtName1.text.toString().trim()
            var number = edtPhone.text.toString().trim()
            var number2 = edtAge.text.toString().trim()




            if (email.isEmpty()) {
                edtEmail.setError("Please fill this input")
                edtEmail.requestFocus()
            } else if (number.isEmpty()) {
                edtPhone.setError("Please fill this input")
                edtPhone.requestFocus()
            } else if (number.length < 10) {
                edtPhone.setError("Password is too short")
                edtPhone.requestFocus()
            } else if (name1.isEmpty()) {
                edtName1.setError("Please Fill this input")
                edtName1.requestFocus()
            } else if (number2.isEmpty()) {
                edtAge.setError("Please Fill this input")
                edtAge.requestFocus()
            } else if (number2.length > 2) {
                edtAge.setError("AGE INCORRECT")
                edtAge.requestFocus()
            } else {

                progress.show()
                mAuth.createUserWithEmailAndPassword(email, number).addOnCompleteListener {
                    progress.dismiss()
                    if (it.isComplete) {
                        Toast.makeText(this, "Continue Upload", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, RegistrationActivity1::class.java))

                        mAuth.signOut()
                        finish()

                    } else {
                        displayMessage("ERROR", it.exception!!.message.toString())
                    }
                }
            }
        }
    }

    fun displayMessage(title: String, message: String) {
        var alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok", null)
        alertDialog.create().show()
    }
}