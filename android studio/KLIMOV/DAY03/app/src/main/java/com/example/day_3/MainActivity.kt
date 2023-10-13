package com.example.day_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity()
{
    var catCount = 0
    var crowCount = 0

    private lateinit var helloButton: AppCompatButton
    private lateinit var catButton: AppCompatButton
    private lateinit var crowButton: AppCompatButton

    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.textView)

        helloButton = findViewById(R.id.helloButton)
        helloButton.setOnClickListener{
            text.text = "Hello pidor"
        }

        crowButton = findViewById(R.id.crowButton)
        crowButton.setOnClickListener{
            text.text = "i see ${++crowCount} crow(-s)"
        }

        catButton = findViewById(R.id.catButton)
        catButton.setOnClickListener{
            text.text = "i see ${++catCount} cat(-s)"
        }

    }
}