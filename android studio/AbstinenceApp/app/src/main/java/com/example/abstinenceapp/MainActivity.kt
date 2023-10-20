package com.example.abstinenceapp

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("UseCompatTextViewDrawableApis")
    fun onClickNavigationBarButtons(view: View)
    {
        val appCompatButton = view as AppCompatButton
        appCompatButton.compoundDrawableTintList = ColorStateList.valueOf(getColor(R.color.burnt_orange))
    }

}