package com.example.abstinenceapp

import android.content.Context

class TimeMethods
{
    companion object
    {
        fun setTimeToMainClock(currT: Long, memT: Long): String
        {
            val days = (currT - memT) / 1440
            val hours = ((currT - memT) % 1440) / 60
            val minutes = (currT - memT) % 60
            return "$days:$hours:$minutes"
        }

        fun putLongToSP(context: Context, key: String, value: Long)
        {
            val sP = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
            val editor = sP.edit()
            editor.putLong(key, value)
            editor.apply()
        }

        fun putStringToSP(context: Context, key: String, value: String)
        {
            val sP = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
            val editor = sP.edit()
            editor.putString(key, value)
            editor.apply()
        }
    }
}