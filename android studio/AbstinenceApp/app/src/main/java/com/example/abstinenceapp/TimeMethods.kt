package com.example.abstinenceapp

import android.content.Context
import android.widget.TextView
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.getLongSP
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.getStringSP
import java.time.LocalDateTime
import java.time.ZoneOffset

class TimeMethods
{
    companion object
    {
        fun getTimeForMainClockTV(differenceInMin: Long): String
        {
            val days = differenceInMin / 1440
            val hours = differenceInMin % 1440 / 60
            val minutes = differenceInMin % 60

            var daysStr = days.toString()
            var hoursStr = hours.toString()
            var minutesStr = minutes.toString()

            if(days < 10) daysStr = "0$days"
            if(hours < 10) hoursStr = "0$hours"
            if(minutes < 10) minutesStr = "0$minutes"

            return "$daysStr:$hoursStr:$minutesStr"
        }

        fun setDateTimeOnTV(context: Context, mTimeWithinADayTV: TextView)
        {
            val appMode = getStringSP(context, "savedAppMode", "smoking")
            val savedEpochMinute = getLongSP(context, "savedTime$appMode", -1)

            val dT = LocalDateTime.ofEpochSecond(savedEpochMinute * 60, 0, ZoneOffset.UTC)
            mTimeWithinADayTV.text = "${dT.dayOfMonth}.${dT.monthValue}.${dT.year}\n${dT.hour}:${dT.minute}"
        }
    }
}