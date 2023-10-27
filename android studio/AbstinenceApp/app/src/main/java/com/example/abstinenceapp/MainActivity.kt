package com.example.abstinenceapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abstinenceapp.CoroutinesMethods.Companion.newThreadCheckAndSetTime
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.getAppModeSP
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.getStringSP
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.saveLongSP
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.saveStringSP
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

//todo при нажатии на кнопку навигационной панели цикл корутинов не прекращается.
// надо сделать так, чтобы с часами было всё корректно
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

                val appMode =
                    getStringSP(this, "savedAppMode", "smoking")
                saveLongSP(this, "savedTime$appMode", currEpochMinute)
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


        mBottleBtn.setOnClickListener {
            saveStringSP(this,"savedAppMode", "drinking")
            getAppModeSP(this,
                mCigaretteBtn, mBottleBtn, mXXXBtn)
        }

        mCigaretteBtn.setOnClickListener {
            saveStringSP(this,"savedAppMode", "smoking")
            getAppModeSP(this,
                mCigaretteBtn, mBottleBtn, mXXXBtn)
        }

        mXXXBtn.setOnClickListener {
            saveStringSP(this,"savedAppMode", "xxx")
            getAppModeSP(this,
                mCigaretteBtn, mBottleBtn, mXXXBtn)
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
        mChroniclesBtn = findViewById(R.id.chroniclesBtn)
        mRestartBtn = findViewById(R.id.restartBtn)
        mSettingsBtn = findViewById(R.id.settingsBtn)
        mStarBtn = findViewById(R.id.achieveBtn)

        mMainClockTV = findViewById(R.id.timeTV)
        activeClockRing = findViewById(R.id.activeClockRing)

        mCigaretteBtn = findViewById(R.id.cigaretteBtn)
        mBottleBtn = findViewById(R.id.bottleBtn)
        mXXXBtn = findViewById(R.id.xxxBtn)

        getAppModeSP(this, mCigaretteBtn, mBottleBtn, mXXXBtn)
    }

}