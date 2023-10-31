package com.example.abstinenceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AchieveActivity : AppCompatActivity()
{
    lateinit var mBackBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achieve)

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