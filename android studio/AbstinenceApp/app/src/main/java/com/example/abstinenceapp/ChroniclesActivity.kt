package com.example.abstinenceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ChroniclesActivity : AppCompatActivity()
{
    lateinit var mBackBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chronicles)

        viewsInitialization()
    }
    override fun onStart()
    {
        super.onStart()

        mBackBtn.setOnClickListener { onBackPressed() }
    }
    private fun viewsInitialization()
    {
        mBackBtn = findViewById(R.id.backBtn)
    }
}