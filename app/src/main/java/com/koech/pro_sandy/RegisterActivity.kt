package com.koech.pro_sandy

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.view.WindowId
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import java.text.FieldPosition
import java.util.jar.Attributes.Name

class RegisterActivity : AppCompatActivity() {

    val gender = arrayOf("Select Gender","Male", "Female", "Other")

    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var edtname1:EditText
    lateinit var edtname2:EditText
    lateinit var btnRegister: Button
    lateinit var progress: ProgressDialog
    lateinit var mAuth: FirebaseAuth

    private fun displayMessage(String: String, toString: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val spinner = findViewById<Spinner>(R.id.spinner2)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,gender)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>? ,
                view: View?,
                position: Int,
                id: Long
            ){
                Toast.makeText(applicationContext, "Select = "+gender[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "No gender Selected", Toast.LENGTH_SHORT).show()
            }
        }

        edtEmail = findViewById(R.id.mEdtemail)
        edtname1 = findViewById(R.id.mEdtName1)
        edtname2 = findViewById(R.id.mEdtName2)
        edtPassword = findViewById(R.id.medtpassword)
        btnRegister = findViewById(R.id.mbtnRegister)
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")

        btnRegister.setOnClickListener {

            var email = edtEmail.text.toString().trim()
            var password = edtPassword.text.toString().toString()
            var name1 = edtname1.text.toString().trim()
            var name2 = edtname2.text.toString().trim()

            if (email.isEmpty()){
                edtEmail.setError("Please fill this input")
                edtEmail.requestFocus()
            } else if (password.isEmpty()){
                edtPassword.setError("Please fill this input")
                edtPassword.requestFocus()
            }else if (name1.isEmpty()){
                edtname1.setError("Please Fill this input")
                edtname1.requestFocus()
            }else if (name2.isEmpty()){
                edtname2.setError("Please Fill this input")
                edtname2.requestFocus()}
            else if(password.length < 6 ){
                edtPassword.setError("Atleast 6 characters.")
                edtPassword.requestFocus()
            } else {

                progress.show()
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    progress.dismiss()
                    if (it.isComplete){
                        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                        mAuth.signOut()
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()

                    } else {
                        displayMessage("ERROR", it.exception!!.message.toString())
                    }
                }
            }

        }
        fun displayMessage(title:String, message:String){
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(title)
            alertDialog.setMessage(message)
            alertDialog.setPositiveButton("Ok", null)
            alertDialog.create().show()
    }
}
}