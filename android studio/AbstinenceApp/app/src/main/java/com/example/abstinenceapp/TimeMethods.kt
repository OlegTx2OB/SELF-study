package com.example.abstinenceapp

import android.content.Context
import android.widget.TextView
import java.time.LocalDateTime
import java.time.ZoneOffset

class TimeMethods
{
    companion object
    {
        fun getTimeForMainClockTV(differenceInMin: Long): String
        {
            val days = differenceInMin / 1440
            val hours = differenceInMin % 1440 / 60 //TODO вот тут что-то не так
            val minutes = differenceInMin % 60

            var daysStr = days.toString()
            var hoursStr = hours.toString()
            var minutesStr = minutes.toString()

            if(days < 10) daysStr = "0$days"
            if(hours < 10) hoursStr = "0$hours"
            if(minutes < 10) minutesStr = "0$minutes"

            return "$daysStr:$hoursStr:$minutesStr"
        }
        fun getTimeInSP(context: Context, defaultValue: Long) : Long
        {
            val key = "savedTime"
            val sP = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            return sP.getLong(key, defaultValue)
        }

        fun getTimeInSP(context: Context) : Long
        {
            val key = "savedTime"
            val sP = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            return sP.getLong(key, -1)//this func is used only in verified cases
        }
        fun saveTimeInSP(context: Context, value: Long)
        {
            val key = "savedTime"
            val sP = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            val editor = sP.edit()
            editor.putLong(key, value)
            editor.apply()
        }

        fun setDateTimeOnTVs(context: Context, mTimeWithinADayTV: TextView)
        {
            val savedEpochMinute = getTimeInSP(context)
            val dT = LocalDateTime.ofEpochSecond(savedEpochMinute * 60, 0, ZoneOffset.UTC)
            mTimeWithinADayTV.text = "${dT.dayOfMonth}.${dT.monthValue}.${dT.year}\n${dT.hour}:${dT.minute}"
        }
    }
}