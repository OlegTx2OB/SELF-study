package com.example.kotlintest0909

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
{

    private lateinit var mHelloTextView: TextView
    private lateinit var mImageButton: ImageButton
    private lateinit var mPlainText: EditText


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("SetTextI18n")
    fun onKitty(view: View)
    {

        mHelloTextView = findViewById(R.id.textView)
        mPlainText = findViewById(R.id.editTextText)
        if(mPlainText.text.isEmpty())
        {
            mHelloTextView.text = "Hey Kitty"
        }
        else mHelloTextView.text = "Hey " + mPlainText.text
    }
}
