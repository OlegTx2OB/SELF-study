package com.example.abstinenceapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate

/*
    all ONTOUCHS and ONCLICKS will be inside the OnStart() method
*/

//todo сделать так, чтобы при запуске таймер не работал
//todo при нажатии на настройки передать какая из кнопок выбора была прожата
//todo мб из-за одинаковых названий (например, back, может отъёбываться приложуха)

abstract class MainActivity : AppCompatActivity()
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
                baseContext, "Press once again to restart!",
                Toast.LENGTH_SHORT
            ).show()
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

            //setClockTimeThread()
        isLoopActive = true
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
                    val daysSinceEpoch = LocalDate.now().toEpochDay().toInt()

                    val sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
                    val calendarTimeInDays = sharedPreferences.getInt("calendarTimeInDays", daysSinceEpoch)
                    val clockTimeInMinutes = sharedPreferences.getInt("clockTimeInMinutes", 0)

                    val days = calendarTimeInDays - daysSinceEpoch
                    val hours: Int = clockTimeInMinutes / 60
                    val minutes = clockTimeInMinutes % 60

                    mTimeTV.text = "$days:$hours:$minutes"
                    activeClockRing.progress = (hours * 60) + minutes
                    delay(60000)
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