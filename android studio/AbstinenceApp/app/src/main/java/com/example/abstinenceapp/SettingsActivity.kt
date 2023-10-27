package com.example.abstinenceapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.getStringSP
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.saveLongSP
import com.example.abstinenceapp.TimeMethods.Companion.setDateTimeOnTVs
import java.time.LocalDateTime
import java.time.ZoneOffset

/*
    all ONTOUCHS and ONCLICKS will be inside the OnStart() method
*/

class SettingsActivity : AppCompatActivity()
{

    lateinit var mBackBtn: ImageButton
    lateinit var mClockBtn: ImageButton

    lateinit var mTimeWithinADayTV: TextView


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        viewsInitialization()
    }

    override fun onStart()
    {
        super.onStart()
//
        mBackBtn.setOnClickListener { onBackPressed() }
//
        mClockBtn.setOnClickListener {
            val currT = LocalDateTime.now()

            var yCopy = 0; var mCopy = 0; var dCopy = 0
            val timePickerDialog = TimePickerDialog(this, { _, hr, min ->

                val selectedDateTime = LocalDateTime.of(yCopy, mCopy, dCopy, hr, min)
                val currDateTime = LocalDateTime.now()

                if(currDateTime.isAfter(selectedDateTime))
                {
                    val appMode =
                        getStringSP(this, "savedAppMode", "smoking")
                    saveLongSP(this, "savedTime$appMode", (selectedDateTime.toEpochSecond(ZoneOffset.UTC) / 60))
                    setDateTimeOnTVs(this, mTimeWithinADayTV)
                }
                else Toast.makeText(this, "selected Date if after current", Toast.LENGTH_SHORT)
                    .show()
            }, currT.hour, currT.minute, true)

            val datePickerDialog = DatePickerDialog(this, { _, y, m, d ->
                yCopy = y; mCopy = m + 1; dCopy = d
                timePickerDialog.show()
            }, currT.year, currT.monthValue - 1, currT.dayOfMonth)//todo цвет стиля последним аргументом можно выбрать. m+1 выбери, месяц неправильно показывается

            datePickerDialog.show()

        }
    }

    override fun onResume()
    {
        super.onResume()
        setDateTimeOnTVs(this, mTimeWithinADayTV)
    }

    private fun viewsInitialization()
    {
        mBackBtn = findViewById(R.id.backBtn)
        mClockBtn = findViewById(R.id.clockBtn)

        mTimeWithinADayTV = findViewById(R.id.dateTimeStartTV)
    }
}