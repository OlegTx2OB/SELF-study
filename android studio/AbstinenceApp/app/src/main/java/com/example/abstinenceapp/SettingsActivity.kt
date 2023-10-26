package com.example.abstinenceapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.abstinenceapp.TimeMethods.Companion.putStringToSP
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
        setTimeOnTV()
    }

    override fun onStart()
    {
        super.onStart()
//
        mBackBtn.setOnClickListener { onBackPressed() }
//
        mClockBtn.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)


            val currFullTimeInMin = (LocalDateTime.now()
                .toEpochSecond(ZoneOffset.UTC) / 60).toInt()

            var yCopy = 0; var mCopy = 0; var dCopy = 0

            val timePickerDialog = TimePickerDialog(this, { _, hr, min ->

                val enteredFullTimeInMin = (LocalDateTime.of(yCopy, mCopy, dCopy, hr, min)
                    .toEpochSecond(ZoneOffset.UTC) / 60).toInt()

                if(enteredFullTimeInMin <= currFullTimeInMin)
                {
                    putStringToSP(this, "mTimeWithinADayTV", "$hr:$min")
                    putStringToSP(this, "mCalendarTimeTV", "$dCopy.${mCopy}.$yCopy")
                    mTimeWithinADayTV.text = "$hr:$min"
                    mCalendarTimeTV.text = "$dCopy.${mCopy}.$yCopy"
                }
                else Toast.makeText(
                    baseContext, "Entered time is > than current", Toast.LENGTH_SHORT).show()

            }, hour, minute, true)

            val datePickerDialog = DatePickerDialog(this, { _, y, m, d ->
                yCopy = y; mCopy = m + 1; dCopy = d
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

    private fun setTimeOnTV()
    {
        val currTimeInMin = (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) / 60)
        val epochStart = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC)
        val currDT = epochStart.plusMinutes(currTimeInMin)

        val sP = getSharedPreferences("preferences", Context.MODE_PRIVATE)
        mTimeWithinADayTV.text = sP.getString("mTimeWithinADayTV", "${currDT.hour}:${currDT.minute}")
        mCalendarTimeTV.text = sP.getString("mCalendarTimeTV",
            "${currDT.dayOfMonth}.${currDT.month}.${currDT.year}")
        //todo время тут тоже обновляется. нужно поставить значение для запоминания
        //todo а еще время берется не из изначального таймера
    }
}