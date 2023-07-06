package com.koech.pro_sandy

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random

class RegistrationActivity1 : AppCompatActivity() {

    lateinit var edtName1: EditText
    lateinit var edtName2: EditText
    lateinit var edtName3: EditText
    lateinit var edtName4: EditText
    lateinit var btnNext: Button
    lateinit var progress: ProgressDialog
    lateinit var mAuth: FirebaseAuth


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration1)

        edtName1 = findViewById(R.id.mStreet)
        edtName2 = findViewById(R.id.mStreet2)
        edtName3 = findViewById(R.id.mCity)
        edtName4 = findViewById(R.id.mCountry)
        btnNext = findViewById(R.id.mbtnproceed)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
        btnNext.setOnClickListener {

            var line1 = edtName1.text.toString().trim()
            var line2 = edtName2.text.toString().trim()
            var line3 = edtName3.text.toString().trim()
            var line4 = edtName4.text.toString().trim()

            if (line1.isEmpty()) {
                edtName1.setError("Please fill this input")
                edtName1.requestFocus()
            } else if (line2.isEmpty()) {
                edtName2.setError("Please fill this input")
                edtName2.requestFocus()
            } else if (line3.isEmpty()){
                edtName3.setError("Fill this input")
                edtName3.requestFocus()
            }else if (line4.isEmpty()){
                edtName4.setError("Please fill this input")
                edtName4.requestFocus()
            }else {
                progress.show()
                mAuth.createUserWithEmailAndPassword(line1, line2).addOnCompleteListener {
                    progress.dismiss()
                    if (it.isComplete) {
                        Toast.makeText(this, "Successful Upload.Now proceed", Toast.LENGTH_SHORT).show()
                        mAuth.signOut()
                        startActivity(Intent(this, RegistrationActivity3::class.java))
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