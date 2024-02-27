package com.example.abstinenceapp

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abstinenceapp.CoroutinesMethods.Companion.newThreadCheckAndSetTime
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.setNavigationBarBtnColors
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.getStringSP
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.loadArrayList
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.saveArrayList
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.saveLongSP
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.saveStringSP
import com.example.abstinenceapp.TimeMethods.Companion.setDateTimeOnTV
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
        var arrayList = ArrayList<String>()
    }

//todo могут возникнуть проблемы вследствие того, что новая активити появляется раньше, чем исчезает старая
    override fun onCreate(savedInstanceState: Bundle?)
    {
        val appMode =
            getStringSP(this, "savedAppMode", "smoking")

        if(appMode == "smoking") setTheme(R.style.Theme_AbstinenceApp_smoking)
        else if(appMode == "drinking") setTheme(R.style.Theme_AbstinenceApp_drinking)
        else setTheme(R.style.Theme_AbstinenceApp_xxx)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arrayList = loadArrayList(this)
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

                arrayList.add(setDateTimeOnTV(this))//todo test
                saveArrayList(this, arrayList)
            }
            else Toast.makeText(this, "Press one more time", Toast.LENGTH_SHORT).show()

            timeFromRestartClick = System.currentTimeMillis()
        }


        mSettingsBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        mStarBtn.setOnClickListener {
            val intent = Intent(this, AchieveActivity::class.java)
            startActivity(intent)
        }

        mChroniclesBtn.setOnClickListener {
            val intent = Intent(this, ChroniclesActivity::class.java)
            startActivity(intent)
        }


        mBottleBtn.setOnClickListener {
            saveStringSP(this,"savedAppMode", "drinking")
            finish()

            val intent = Intent(this, MainActivity::class.java)
            val options =
                ActivityOptions.makeCustomAnimation(this, R.anim.slide_in, R.anim.slide_out)
            startActivity(intent, options.toBundle())
        }

        mCigaretteBtn.setOnClickListener {
            saveStringSP(this,"savedAppMode", "smoking")
            finish()

            val intent = Intent(this, MainActivity::class.java)
            val options =
                ActivityOptions.makeCustomAnimation(this, R.anim.slide_in, R.anim.slide_out)
            startActivity(intent, options.toBundle())

        }

        mXXXBtn.setOnClickListener {
            saveStringSP(this,"savedAppMode", "xxx")
            finish()

            val intent = Intent(this, MainActivity::class.java)
            val options =
                ActivityOptions.makeCustomAnimation(this, R.anim.slide_in, R.anim.slide_out)
            startActivity(intent, options.toBundle())
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

        setNavigationBarBtnColors(this, mCigaretteBtn, mBottleBtn, mXXXBtn)
    }

}