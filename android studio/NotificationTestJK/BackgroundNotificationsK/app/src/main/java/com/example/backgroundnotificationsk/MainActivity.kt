package com.example.backgroundnotificationsk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val buttonReceive = findViewById<Button>(R.id.buttonReceive)


        button.setOnClickListener {

            val sP = this.getSharedPreferences("mysp", Context.MODE_PRIVATE)
            button.text = sP.getString("sp6", "NOT FOUND")
        }

        buttonReceive.setOnClickListener {


            

            val serviceIntent = Intent(this, MyService::class.java)
            startService(serviceIntent)


        }
    }
}