package com.example.abstinenceapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Calendar

/*
    all ONTOUCHS and ONCLICKS will be inside the OnStart() method
*/

class SettingsActivity : AppCompatActivity()
{
    //todo написать, чтобы по умолчанию время бралось как нынешнее(если до этого не заходил ты в приложуху)

    lateinit var mBackBtn: ImageButton
    lateinit var mClockBtn: ImageButton

    lateinit var mCalendarTimeTV: TextView
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

        mBackBtn.setOnClickListener { onBackPressed() }

        mClockBtn.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)


            val currFullTimeInMin = (LocalDateTime.now()
                .toEpochSecond(ZoneOffset.UTC) / 60).toInt()
            var enteredFullTimeInMin: Int = 0
            var enteredY = 0
            var enteredM = 0
            var enteredD = 0

            val timePickerDialog = TimePickerDialog(this, { _, hr, min ->
                enteredFullTimeInMin += (hr * 60) + min

                if(enteredFullTimeInMin <= currFullTimeInMin)//todo неверный подсчет enteredFullTimeInMin(погрешность в около 2 недели. типа 24 год не пропускает, но близкие даты - да)
                {
                    mTimeWithinADayTV.text = "$hr:$min"
                    mCalendarTimeTV.text = "$enteredD.${enteredM + 1}.$enteredY"
                }
                else Toast.makeText(
                    baseContext, "Entered time is > than current", Toast.LENGTH_SHORT).show()

            }, hour, minute, true)

            val datePickerDialog = DatePickerDialog(this, { _, y, m, d ->

                enteredFullTimeInMin = (LocalDateTime.of(y, m, d, 0, 0)
                    .toEpochSecond(ZoneOffset.UTC) / 60).toInt()

                enteredY = y; enteredM = m; enteredD = d;

                timePickerDialog.show()
            }, year, month, day)//todo цвет стиля последним аргументом можно выбрать

            datePickerDialog.show()

        }
    }

    private fun viewsInitialization()
    {
        mBackBtn = findViewById(R.id.backBtn)
        mClockBtn = findViewById(R.id.clockBtn)

        mCalendarTimeTV = findViewById(R.id.dateTV)
        mTimeWithinADayTV = findViewById(R.id.timeWithinADayTV)
    }
}