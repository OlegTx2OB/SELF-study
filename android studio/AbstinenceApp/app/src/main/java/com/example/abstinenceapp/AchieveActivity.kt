package com.example.abstinenceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AchieveActivity : AppCompatActivity()
{
    lateinit var mBackBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?)
    {
        val appMode =
            SharedPreferencesMethods.getStringSP(this, "savedAppMode", "smoking")

        if(appMode == "smoking") setTheme(R.style.Theme_AbstinenceApp_smoking)
        else if(appMode == "drinking") setTheme(R.style.Theme_AbstinenceApp_drinking)
        else setTheme(R.style.Theme_AbstinenceApp_xxx)

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