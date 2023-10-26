package com.example.abstinenceapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abstinenceapp.CoroutinesMethods.Companion.setClockTimeThread
import com.example.abstinenceapp.TimeMethods.Companion.putIntToSP
import com.example.abstinenceapp.TimeMethods.Companion.setTimeToMainClock
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.ZoneOffset

/*
    all ONTOUCHS and ONCLICKS will be inside the OnStart() method
*/

//todo сделать так, чтобы при запуске таймер не работал
//todo при нажатии на настройки передать какая из кнопок выбора была прожата
//todo мб из-за одинаковых названий (например, back, может отъёбываться приложуха)

class MainActivity : AppCompatActivity()
{
    private lateinit var mCigaretteBtn: ImageButton
    private lateinit var mBottleBtn: ImageButton
    private lateinit var mXXXBtn: ImageButton

    private lateinit var mMenuBtn: ImageButton
    private lateinit var mRestartBtn: ImageButton
    private lateinit var mSettingsBtn: ImageButton
    private lateinit var mAchieveBtn: ImageButton

    private lateinit var mMainClockTV: TextView
    private lateinit var activeClockRing: ProgressBar

    var isLoopActive = true
    val context = this


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewsInitialization()
    }

    override fun onStart()
    {
        super.onStart()

//
        var timePicker: Long = 0
        mRestartBtn.setOnClickListener{
            if (timePicker + 2000 > System.currentTimeMillis())
            {
                val currTimeInMin = (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) / 60).toInt()
                putIntToSP(this, "currTimeInMin", currTimeInMin)
            }
            else Toast.makeText(
                baseContext, "Press once again to restart!", Toast.LENGTH_SHORT).show()

            timePicker = System.currentTimeMillis()
        }

//
        mSettingsBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume()
    {
        super.onResume()

        isLoopActive = true
        setClockTimeThread(this, isLoopActive, mMainClockTV, activeClockRing)
    }

    override fun onPause()
    {
        super.onPause()
        isLoopActive = false
    }
    private fun viewsInitialization()
    {
        mCigaretteBtn = findViewById(R.id.cigaretteBtn)
        mBottleBtn = findViewById(R.id.bottleBtn)
        mXXXBtn = findViewById(R.id.xxxBtn)

        mMenuBtn = findViewById(R.id.archiveBtn)
        mRestartBtn = findViewById(R.id.restartBtn)
        mSettingsBtn = findViewById(R.id.settingsBtn)
        mAchieveBtn = findViewById(R.id.achieveBtn)

        mMainClockTV = findViewById(R.id.timeTV)
        activeClockRing = findViewById(R.id.activeClockRing)
    }

}