package com.example.day20k

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


private var backPressed: Long = 0

class MainActivity : AppCompatActivity()
{
    lateinit var editSearch: EditText
    public override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @Suppress("DEPRECATION")
    override fun onBackPressed()
    {
        if (backPressed + 2000 > System.currentTimeMillis()) super.onBackPressed()
        else
        {
            Toast.makeText(
                baseContext, "Press once again to exit!",
                Toast.LENGTH_SHORT
            ).show()
        }
        backPressed = System.currentTimeMillis()
    }
}