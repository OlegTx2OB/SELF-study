package com.example.day_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity()
{

    private lateinit var layout: ConstraintLayout
    private lateinit var redButton: Button
    private lateinit var yellowButton: Button
    private lateinit var greenButton: Button
    private lateinit var text: TextView


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.textView)
        layout = findViewById(R.id.layout)
    }

    fun onButtonClick(view: View)
    {
        when(view.id)
        {
            R.id.redButton ->
            {
                layout.setBackgroundColor(resources.getColor(R.color.red, null))
                text.text = "Красный"
            }
            R.id.yellowButton ->
            {
                layout.setBackgroundColor(resources.getColor(R.color.yellow, null))
                text.text = "Желтый"

            }
            else ->
            {
                layout.setBackgroundColor(resources.getColor(R.color.green, null))
                text.text = "Зеленый"

            }
        }
    }
}