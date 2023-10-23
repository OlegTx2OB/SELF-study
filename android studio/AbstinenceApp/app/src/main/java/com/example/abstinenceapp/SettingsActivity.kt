package com.example.abstinenceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class SettingsActivity : AppCompatActivity()
{
    //todo написать, чтобы по умолчанию время бралось как нынешнее(если до этого не заходил ты в приложуху)
    lateinit var mBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        viewsInitialization()

        mBack.setOnClickListener { onBackPressed() }
    }

    private fun viewsInitialization()
    {
        mBack = findViewById(R.id.back)
    }
}