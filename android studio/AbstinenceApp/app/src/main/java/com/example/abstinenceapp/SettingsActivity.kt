package com.example.abstinenceapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ImageButton
import android.widget.TextView
import java.util.Calendar

/*
    all ONTOUCHS and ONCLICKS will be inside the OnResume() method
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

    override fun onResume()
    {
        super.onResume()

        mBackBtn.setOnClickListener { onBackPressed() }

        mCalendarBtn.setOnClickListener {
            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, y, m, d ->
                mCalendarTimeTV.text = "$d.${m + 1}.$y"
//                val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
//                val edit = sharedPreferences.edit();
//                edit.putBoolean("sly", false);
//                edit.commit();
            }, year, month, day)//todo цвет стиля последним аргументом можно выбрать

            datePickerDialog.show()
        }

        mClockBtn.setOnClickListener {
            val calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hr, min ->
                mClockTimeTV.text = "$hr.$min"

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