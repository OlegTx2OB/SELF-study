package com.example.abstinenceapp

import android.content.Context
import android.widget.ImageButton

class SharedPreferencesMethods
{
    companion object
    {
        fun getLongSP(context: Context, key: String, defaultValue: Long) : Long
        {
            val sP = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            return sP.getLong(key, defaultValue)
        }

        fun saveLongSP(context: Context, key: String, value: Long)//"savedTime"
        {
            val sP = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            val editor = sP.edit()
            editor.putLong(key, value)
            editor.apply()
        }

        fun getAppModeSP(context: Context,
                         mCigaretteBtn: ImageButton, mBottleBtn: ImageButton, mXXXBtn: ImageButton)//todo
        {
            val appMode = getStringSP(context, "savedAppMode", "smoking")
            if(appMode == "smoking")
            {
                mCigaretteBtn.isEnabled = false
            }
            else if(appMode == "drinking")
            {
                mBottleBtn.isEnabled = false

            }
            else
            {
                mXXXBtn.isEnabled = false

            }
        }
        fun getStringSP(context: Context, key: String, defaultValue: String) : String?
        {
            val sP = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            return sP.getString(key, defaultValue)
        }

        fun saveStringSP(context: Context, key: String, value: String)//"savedAppMode"
        {
            val sP = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            val editor = sP.edit()
            editor.putString(key, value)
            editor.apply()
        }
    }
}