package com.example.abstinenceapp

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity()
{

    lateinit var mCigaretteBtn: AppCompatButton
    lateinit var mBottleBtn: AppCompatButton
    lateinit var mXXXBtn: AppCompatButton

    lateinit var mMenuBtn: ImageButton
    lateinit var mRestartBtn: ImageButton
    lateinit var mSettingsBtn: ImageButton
    lateinit var mAchieveBtn: ImageButton



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewsInitialization()
    }

    fun viewsInitialization()
    {
        mCigaretteBtn = findViewById(R.id.cigarette)
        mBottleBtn = findViewById(R.id.bottle)
        mXXXBtn = findViewById(R.id.xxx)

        mMenuBtn = findViewById(R.id.menu)
        mRestartBtn = findViewById(R.id.restart)
        mSettingsBtn = findViewById(R.id.settings)
        mAchieveBtn = findViewById(R.id.achieve)
    }
    @SuppressLint("UseCompatTextViewDrawableApis")
    fun onClickNavigationBarButtons(view: View)
    {
        val pressedButton = view as AppCompatButton

        val disabledButton: AppCompatButton = if(!mCigaretteBtn.isEnabled) mCigaretteBtn
        else if(!mBottleBtn.isEnabled) mBottleBtn
        else mXXXBtn

        pressedButton.compoundDrawableTintList = ColorStateList.valueOf(
            getColor(R.color.burnt_orange))
        pressedButton.isEnabled = false

        disabledButton.compoundDrawableTintList = ColorStateList.valueOf(
            getColor(R.color.dark_chocolate))
        disabledButton.isEnabled = true
    }

}