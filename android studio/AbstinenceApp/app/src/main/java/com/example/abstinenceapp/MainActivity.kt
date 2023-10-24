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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private lateinit var mCigaretteBtn: ImageButton
    private lateinit var mBottleBtn: ImageButton
    private lateinit var mXXXBtn: ImageButton

    private lateinit var mMenuBtn: ImageButton
    private lateinit var mRestartBtn: ImageButton
    private lateinit var mSettingsBtn: ImageButton
    private lateinit var mAchieveBtn: ImageButton

    private lateinit var mTimeTV: TextView
    private lateinit var activeClockRing: ProgressBar

    var isLoopActive = true


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewsInitialization()
    }

    override fun onStart()
    {
        super.onStart()

        var timePicker: Long = 0
        mRestartBtn.setOnClickListener{
            if (timePicker + 2000 > System.currentTimeMillis()) mTimeTV.text = "Meow"//todo
            else Toast.makeText(
                baseContext, "Press once again to restart!", Toast.LENGTH_SHORT).show()
            timePicker = System.currentTimeMillis()
        }

        mSettingsBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume()
    {
        super.onResume()

        isLoopActive = true
        setClockTimeThread()
    }

    override fun onPause()
    {
        super.onPause()
        isLoopActive = false

    }

    fun setClockTimeThread()
    {
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            GlobalScope.launch(Dispatchers.Main) {
                while (isLoopActive)
                {
                    val currFullTimeInMin = (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) / 60).toInt()

                    val sP = getSharedPreferences("preferences", Context.MODE_PRIVATE)

                    val fullMemTimeInMin = sP.getInt("fullMemTimeInMin", currFullTimeInMin)
                    if(fullMemTimeInMin == currFullTimeInMin)
                    {
                        val editor = sP.edit()
                        editor.putInt("fullMemTimeInMin", currFullTimeInMin)
                        editor.apply()
                    }
                    val days: Int = (currFullTimeInMin - fullMemTimeInMin) / 1440
                    val hours: Int = ((currFullTimeInMin - fullMemTimeInMin) % 1440) / 60
                    val minutes: Int = (currFullTimeInMin - fullMemTimeInMin) % 60

                    mTimeTV.text = "$days:$hours:$minutes"
                    activeClockRing.progress = (currFullTimeInMin - fullMemTimeInMin) % 1440
                    delay(1000)//todo 60000



//                    val memTimeInMinutes = sP.getInt("clockTimeInMinutes", currTimeInMinutes)
//                    if (memTimeInMinutes == currTimeInMinutes)
//                    {
//                        val editor = sP.edit()
//                        editor.putInt("clockTimeInMinutes", currTimeInMinutes)
//                        editor.apply()
//                    }
//                    val days = memTimeInDays - daysSinceEpoch
//                    val hours: Int = memTimeInMinutes / 60
//                    val minutes = memTimeInMinutes % 60
//

                }
            }
        }
        handler.post(runnable)
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

        mTimeTV = findViewById(R.id.timeTV)
        activeClockRing = findViewById(R.id.activeClockRing)
    }

}