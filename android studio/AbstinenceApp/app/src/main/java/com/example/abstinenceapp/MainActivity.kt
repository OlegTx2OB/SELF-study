package com.example.abstinenceapp

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity()
{

    lateinit var cigaretteButton: AppCompatButton
    lateinit var bottleButton: AppCompatButton
    lateinit var xxxButton: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cigaretteButton = findViewById(R.id.cigarette)
        bottleButton = findViewById(R.id.bottle)
        xxxButton = findViewById(R.id.xxx)
    }

    @SuppressLint("UseCompatTextViewDrawableApis")
    fun onClickNavigationBarButtons(view: View)
    {
        val pressedButton = view as AppCompatButton

        val disabledButton: AppCompatButton = if(!cigaretteButton.isEnabled) cigaretteButton
        else if(!bottleButton.isEnabled) bottleButton
        else xxxButton

        pressedButton.compoundDrawableTintList = ColorStateList.valueOf(
            getColor(R.color.burnt_orange))
        pressedButton.isEnabled = false

        disabledButton.compoundDrawableTintList = ColorStateList.valueOf(
            getColor(R.color.charcoal))
        disabledButton.isEnabled = true

    }

}