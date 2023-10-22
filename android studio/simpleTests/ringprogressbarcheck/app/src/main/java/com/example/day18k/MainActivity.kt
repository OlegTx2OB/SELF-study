package com.example.day18k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity()
{
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var isLoopActive = true
    private var intProgress = 0
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<ProgressBar>(R.id.circular_progress_bar)
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)

        button.setOnClickListener { textView.text = (++count).toString() }


        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            GlobalScope.launch(Dispatchers.Main) {
                while (isLoopActive)
                {
                    if (intProgress == progressBar.max)
                    {
                        intProgress = 0
                        textView.text = (++count).toString()
                    }
                    progressBar.progress = intProgress++
                    delay(20)
                }
            }
        }
    }

    override fun onResume()
    {
        super.onResume()
        isLoopActive = true
        handler.post(runnable)
    }
    override fun onStop()
    {
        super.onStop()
        isLoopActive = false
    }
}