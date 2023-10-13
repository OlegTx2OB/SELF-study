package com.example.day_9_k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity()
{

    lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button31)


        button1.setOnClickListener{
            val text = "Пора покормить кота!"
            val duration = Toast.LENGTH_SHORT

            var toast = Toast.makeText(applicationContext, text, duration)
            toast.setGravity(Gravity.TOP or Gravity.LEFT, 0, 0)
            toast.show()
        }
    }
}