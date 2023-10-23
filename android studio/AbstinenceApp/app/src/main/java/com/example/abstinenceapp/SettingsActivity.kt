package com.example.abstinenceapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import androidx.core.content.ContextCompat

class SettingsActivity : AppCompatActivity()
{

    lateinit var dayPicker: NumberPicker
    lateinit var monthPicker: NumberPicker
    lateinit var yearPicker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        yearPicker = findViewById(R.id.yearPicker)
        yearPicker.minValue = 2020
        yearPicker.maxValue = 2030
        yearPicker.value = 2023
        val color = ContextCompat.getColor(this, R.color.dark_chocolate)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            yearPicker.textColor = color
    }

    private fun viewsInitialization()
    {

    }
}