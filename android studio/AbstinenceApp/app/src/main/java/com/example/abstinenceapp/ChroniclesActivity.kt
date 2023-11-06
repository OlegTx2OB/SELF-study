package com.example.abstinenceapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.example.abstinenceapp.MainActivity.Companion.arrayList

class ChroniclesActivity : AppCompatActivity()
{
    lateinit var mBackBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?)
    {
        val appMode =
            SharedPreferencesMethods.getStringSP(this, "savedAppMode", "smoking")

        if(appMode == "smoking") setTheme(R.style.Theme_AbstinenceApp_smoking)
        else if(appMode == "drinking") setTheme(R.style.Theme_AbstinenceApp_drinking)
        else setTheme(R.style.Theme_AbstinenceApp_xxx)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chronicles)

        viewsInitialization()
        addChroniclesColumn()
    }
    override fun onStart()
    {
        super.onStart()

        mBackBtn.setOnClickListener { onBackPressed() }
    }
    private fun viewsInitialization()
    {
        mBackBtn = findViewById(R.id.backBtn)
    }

    private fun addChroniclesColumn()
    {
        val linearLayout: LinearLayout = findViewById(R.id.linearLayout)

        arrayList.reversed().forEach { s ->
            val constraintLayout = ConstraintLayout(this)
            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                resources.getDimensionPixelSize(R.dimen.constraint_l_height)
            )
            constraintLayout.layoutParams = layoutParams
            constraintLayout.setPadding(
                resources.getDimensionPixelSize(R.dimen.constraint_l_horizontal_padding),
                resources.getDimensionPixelSize(R.dimen.constraint_l_vertical_padding),
                resources.getDimensionPixelSize(R.dimen.constraint_l_horizontal_padding),
                resources.getDimensionPixelSize(R.dimen.constraint_l_vertical_padding)
            )
            linearLayout.addView(constraintLayout)

            val view = View(this)
            view.setBackgroundResource(R.drawable.frame)
            val viewParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            view.layoutParams = viewParams
            constraintLayout.addView(view)


            val textView = TextView(this)
            textView.text = s
            val typeface = Typeface.create("sans-serif-black", Typeface.NORMAL)
            textView.typeface = typeface
            textView.id = View.generateViewId()
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            val appMode = SharedPreferencesMethods.getStringSP(this, "savedAppMode", "smoking")
            val textColor: Int = if(appMode == "drinking") ContextCompat.getColor(this, R.color.gunmetal)
            else if(appMode == "smoking") ContextCompat.getColor(this, R.color.dark_chocolate)
            else ContextCompat.getColor(this, R.color.white)
            textView.setTextColor(textColor)
            val textParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            textParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            textParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            textParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            textParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            textView.layoutParams = textParams
            constraintLayout.addView(textView)
        }
    }
}