package com.example.abstinenceapp

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageButton
import androidx.core.content.ContextCompat

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
            val burntOrange = ContextCompat.getColor(context, R.color.burnt_orange)
            val darkChocolate = ContextCompat.getColor(context, R.color.dark_chocolate)

            val appMode = getStringSP(context, "savedAppMode", "smoking")

            if(appMode == "drinking")
            {
                mBottleBtn.isEnabled = false
                mCigaretteBtn.isEnabled = true
                mXXXBtn.isEnabled = true

                mBottleBtn.imageTintList = ColorStateList.valueOf(burntOrange)
                mCigaretteBtn.imageTintList = ColorStateList.valueOf(darkChocolate)
                mXXXBtn.imageTintList = ColorStateList.valueOf(darkChocolate)
            }
            else if(appMode == "smoking")
            {
                mBottleBtn.isEnabled = true
                mCigaretteBtn.isEnabled = false
                mXXXBtn.isEnabled = true

                mBottleBtn.imageTintList = ColorStateList.valueOf(darkChocolate)
                mCigaretteBtn.imageTintList = ColorStateList.valueOf(burntOrange)
                mXXXBtn.imageTintList = ColorStateList.valueOf(darkChocolate)
            }
            else
            {
                mBottleBtn.isEnabled = true
                mCigaretteBtn.isEnabled = true
                mXXXBtn.isEnabled = false

                mBottleBtn.imageTintList = ColorStateList.valueOf(darkChocolate)
                mCigaretteBtn.imageTintList = ColorStateList.valueOf(darkChocolate)
                mXXXBtn.imageTintList = ColorStateList.valueOf(burntOrange)
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