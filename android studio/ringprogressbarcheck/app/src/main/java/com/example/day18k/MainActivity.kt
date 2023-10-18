package com.example.day18k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

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

                progressBar.progress = intProgress++
                if (intProgress == progressBar.max) intProgress = 0
                Thread.sleep(500)
            progressBar.progress = 500
            Thread.sleep(1000)
            progressBar.progress = 200
            Thread.sleep(3000)
            progressBar.progress = 800//todo почему-то замораживается основной поток на это время(точнее, отрисовка не работает)

        }
        handler.post(runnable)
    }

    override fun onPause()
    {
        super.onPause()
        isLoopActive = false
    }
}