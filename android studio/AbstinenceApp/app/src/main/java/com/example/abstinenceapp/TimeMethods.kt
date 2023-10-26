package com.example.abstinenceapp

import android.content.Context

class TimeMethods
{
    companion object
    {
        fun setTimeToMainClock(currT: Int, memT: Int): String
        {
            val days: Int = (currT - memT) / 1440
            val hours: Int = ((currT - memT) % 1440) / 60
            val minutes: Int = (currT - memT) % 60
            return "$days:$hours:$minutes"
        }

        fun putIntToSP(context: Context, key: String, value: Int)
        {
            val sP = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
            val editor = sP.edit()
            editor.putInt(key, value)
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