package com.koech.pro_sandy

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.koech.pro_sandy.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request


class RegistrationActivity4 : AppCompatActivity() {

    lateinit var one: ImageView
    lateinit var two: ImageView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration4)

        one = findViewById(R.id.Math)
        two = findViewById(R.id.Chem)

        one.setOnClickListener {
                val url = "https://www.mathsisfun.com/"
                // Replace with your desired URL

                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(url)
                    .build()

                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val content = response.body?.string()
                    println(content)
                } else {
                    println("Failed to fetch content from the URL.")
                }
            }

        two.setOnClickListener {
                val url = "https://chem.libretexts.org/Bookshelves"
                // Replace with your desired URL

                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(url)
                    .build()

                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val content = response.body?.string()
                    println(content)
                } else {
                    println("Failed to fetch content from the URL.")
                }
        }
    }
}