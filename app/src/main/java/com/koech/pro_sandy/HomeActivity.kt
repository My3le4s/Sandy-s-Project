package com.koech.pro_sandy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {

    lateinit var Btnstart:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        Btnstart = findViewById(R.id.mBTNstart)

        Btnstart.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
}