package com.koech.pro_sandy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var BtnReg:Button
    lateinit var BtnLogIn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BtnReg = findViewById(R.id.BtnRegister)
        BtnLogIn = findViewById(R.id.BtnLogin)

        BtnReg.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
        BtnLogIn.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))
        }
    }
}