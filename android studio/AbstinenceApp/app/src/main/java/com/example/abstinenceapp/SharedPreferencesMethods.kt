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

        fun setNavigationBarBtnColors(context: Context,
                                      mCigaretteBtn: ImageButton, mBottleBtn: ImageButton, mXXXBtn: ImageButton)//todo
        {
            val activeColor = ContextCompat.getColor(context, R.color.burnt_orange)
            val passiveColor = ContextCompat.getColor(context, R.color.dark_chocolate)

            
            val appMode = getStringSP(context, "savedAppMode", "smoking")

            if(appMode == "drinking")
            {
                mBottleBtn.isEnabled = false
                mCigaretteBtn.isEnabled = true
                mXXXBtn.isEnabled = true

                mBottleBtn.imageTintList = ColorStateList.valueOf(activeColor)
                mCigaretteBtn.imageTintList = ColorStateList.valueOf(passiveColor)
                mXXXBtn.imageTintList = ColorStateList.valueOf(passiveColor)
            }
            else if(appMode == "smoking")
            {
                mBottleBtn.isEnabled = true
                mCigaretteBtn.isEnabled = false
                mXXXBtn.isEnabled = true

                mBottleBtn.imageTintList = ColorStateList.valueOf(passiveColor)
                mCigaretteBtn.imageTintList = ColorStateList.valueOf(activeColor)
                mXXXBtn.imageTintList = ColorStateList.valueOf(passiveColor)
            }
            else
            {
                mBottleBtn.isEnabled = true
                mCigaretteBtn.isEnabled = true
                mXXXBtn.isEnabled = false

                mBottleBtn.imageTintList = ColorStateList.valueOf(passiveColor)
                mCigaretteBtn.imageTintList = ColorStateList.valueOf(passiveColor)
                mXXXBtn.imageTintList = ColorStateList.valueOf(activeColor)
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