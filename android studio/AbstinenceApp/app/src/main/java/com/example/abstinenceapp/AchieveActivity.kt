package com.example.abstinenceapp

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import java.time.LocalDateTime
import java.time.ZoneOffset

class AchieveActivity : AppCompatActivity()
{
    lateinit var mBackBtn: ImageButton
    lateinit var f1: View; lateinit var f2: View; lateinit var f3: View; lateinit var f4: View;
    lateinit var f5: View; lateinit var f6: View; lateinit var f7: View; lateinit var f8: View;
    lateinit var f9: View; lateinit var f10: View;


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

        colouringAchievedAchievements()
    }

    override fun onStart()
    {
        super.onStart()

        mBackBtn.setOnClickListener { onBackPressed() }
    }
    private fun viewsInitialization()
    {
        mBackBtn = findViewById(R.id.backBtn)

        f1 = findViewById(R.id.f1)
        f2 = findViewById(R.id.f2)
        f3 = findViewById(R.id.f3)
        f4 = findViewById(R.id.f4)
        f5 = findViewById(R.id.f5)
        f6 = findViewById(R.id.f6)
        f7 = findViewById(R.id.f7)
        f8 = findViewById(R.id.f8)
        f9 = findViewById(R.id.f9)
        f10 = findViewById(R.id.f10)
    }

    fun getTimeDifferenceInMin(): Long
    {
        val currEpochMinute = LocalDateTime.now()
            .toEpochSecond(ZoneOffset.UTC) / 60
        val appMode = SharedPreferencesMethods
                .getStringSP(this, "savedAppMode", "smoking")
        val savedEpochMinute = SharedPreferencesMethods
            .getLongSP(this, "savedTime$appMode", 0)

        return currEpochMinute - savedEpochMinute
    }
    fun colouringAchievedAchievements()
    {
        val minuteDifference = getTimeDifferenceInMin()

        val dayInMinutes: Long = 1440

        val appMode = SharedPreferencesMethods
            .getStringSP(this, "savedAppMode", "smoking")
        var activeColor: Int
        if (appMode == "smoking") activeColor = ContextCompat.getColor(this, R.color.burnt_orange)
        else if (appMode == "drinking") activeColor = ContextCompat.getColor(this, R.color.pink)
        else activeColor = ContextCompat.getColor(this, R.color.orange)

        if(minuteDifference >= 360) f1.background.setColorFilter(activeColor, PorterDuff.Mode.SRC_ATOP)
        if(minuteDifference >= dayInMinutes) f2.background.setColorFilter(activeColor, PorterDuff.Mode.SRC_ATOP)
        if(minuteDifference >= dayInMinutes * 3) f3.background.setColorFilter(activeColor, PorterDuff.Mode.SRC_ATOP)
        if(minuteDifference >= dayInMinutes * 5) f4.background.setColorFilter(activeColor, PorterDuff.Mode.SRC_ATOP)
        if(minuteDifference >= dayInMinutes * 7) f5.background.setColorFilter(activeColor, PorterDuff.Mode.SRC_ATOP)
        if(minuteDifference >= dayInMinutes * 10) f6.background.setColorFilter(activeColor, PorterDuff.Mode.SRC_ATOP)
        if(minuteDifference >= dayInMinutes * 15) f7.background.setColorFilter(activeColor, PorterDuff.Mode.SRC_ATOP)
        if(minuteDifference >= dayInMinutes * 20) f8.background.setColorFilter(activeColor, PorterDuff.Mode.SRC_ATOP)
        if(minuteDifference >= dayInMinutes * 25) f9.background.setColorFilter(activeColor, PorterDuff.Mode.SRC_ATOP)
        if(minuteDifference >= dayInMinutes * 30) f10.background.setColorFilter(activeColor, PorterDuff.Mode.SRC_ATOP)

    }
}