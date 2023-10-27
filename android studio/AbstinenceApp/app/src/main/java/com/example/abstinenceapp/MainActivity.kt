package com.example.abstinenceapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abstinenceapp.CoroutinesMethods.Companion.newThreadCheckAndSetTime
import com.example.abstinenceapp.TimeMethods.Companion.saveTimeInSP
import java.time.LocalDateTime
import java.time.ZoneOffset


/*
    all ONTOUCHS and ONCLICKS will be inside the OnStart() method
*/

class MainActivity : AppCompatActivity()
{
    private lateinit var mCigaretteBtn: ImageButton
    private lateinit var mBottleBtn: ImageButton
    private lateinit var mXXXBtn: ImageButton

    private lateinit var mChroniclesBtn: ImageButton
    private lateinit var mRestartBtn: ImageButton
    private lateinit var mSettingsBtn: ImageButton
    private lateinit var mStarBtn: ImageButton

    private lateinit var mMainClockTV: TextView
    private lateinit var activeClockRing: ProgressBar

    companion object
    {
        var isLoopActive = true
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewsInitialization()

    }

    override fun onStart()
    {
        super.onStart()

        var timeFromRestartClick: Long = 0
        mRestartBtn.setOnClickListener{
            if(timeFromRestartClick + 2000 > System.currentTimeMillis())
            {
                val currEpochMinute = LocalDateTime.now()
                    .toEpochSecond(ZoneOffset.UTC) / 60
                saveTimeInSP(this, currEpochMinute)
            }
            else Toast.makeText(this, "Press one more time", Toast.LENGTH_SHORT).show()

            timeFromRestartClick = System.currentTimeMillis()
        }


        mSettingsBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        mStarBtn.setOnClickListener {//todo
            val intent = Intent(this, AchieveActivity::class.java)
            startActivity(intent)

        }

        mChroniclesBtn.setOnClickListener {//todo

        }
    }

    override fun onResume()
    {
        super.onResume()
        isLoopActive = true
        newThreadCheckAndSetTime(this, mMainClockTV, activeClockRing)
    }

    override fun onStop()
    {
        super.onStop()
        isLoopActive = false
    }
    private fun viewsInitialization()
    {
        mCigaretteBtn = findViewById(R.id.cigaretteBtn)
        mBottleBtn = findViewById(R.id.bottleBtn)
        mXXXBtn = findViewById(R.id.xxxBtn)

        mChroniclesBtn = findViewById(R.id.chroniclesBtn)
        mRestartBtn = findViewById(R.id.restartBtn)
        mSettingsBtn = findViewById(R.id.settingsBtn)
        mStarBtn = findViewById(R.id.achieveBtn)

        mMainClockTV = findViewById(R.id.timeTV)
        activeClockRing = findViewById(R.id.activeClockRing)
    }

}