package com.example.abstinenceapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import java.time.LocalDate
import java.util.Calendar

/*
    all ONTOUCHS and ONCLICKS will be inside the OnStart() method
*/

class SettingsActivity : AppCompatActivity()
{
    //todo написать, чтобы по умолчанию время бралось как нынешнее(если до этого не заходил ты в приложуху)

    lateinit var mBackBtn: ImageButton
    lateinit var mCalendarBtn: ImageButton
    lateinit var mClockBtn: ImageButton

    lateinit var mCalendarTimeTV: TextView
    lateinit var mClockTimeTV: TextView


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        viewsInitialization()
    }

    override fun onStart()
    {
        super.onStart()

        mBackBtn.setOnClickListener { onBackPressed() }

        mCalendarBtn.setOnClickListener {
            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, y, m, d ->
                mCalendarTimeTV.text = "$d.${m + 1}.$y"
                val sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                val daysCount = LocalDate.of(y, m+1, d).toEpochDay().toInt()
                editor.putInt("calendarTimeInDays", daysCount)
                editor.apply()
            }, year, month, day)//todo цвет стиля последним аргументом можно выбрать

            datePickerDialog.show()
        }

        mClockBtn.setOnClickListener {
            val calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hr, min ->
                mClockTimeTV.text = "$hr.$min"

                val sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                val minutesCount = hr * 60 + min
                editor.putInt("clockTimeInMinutes", minutesCount)
                editor.apply()

            }, hour, minute, true)

            timePickerDialog.show()
        }
    }

    private fun viewsInitialization()
    {
        mBackBtn = findViewById(R.id.backBtn)
        mCalendarBtn = findViewById(R.id.calendarBtn)
        mClockBtn = findViewById(R.id.clockBtn)

        mCalendarTimeTV = findViewById(R.id.calendarTimeTV)
        mClockTimeTV = findViewById(R.id.clockTimeTV)
    }
}