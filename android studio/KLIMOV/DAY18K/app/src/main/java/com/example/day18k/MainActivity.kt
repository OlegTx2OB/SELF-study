package com.example.day18k

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sunImageView: ImageView = findViewById(R.id.sun)
        val clockImageView: ImageView = findViewById(R.id.clock)
        val sunRiseAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.sun_rise)
        val clockTurnAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.clock_turn)
        sunImageView.startAnimation(sunRiseAnimation)
        clockImageView.startAnimation(clockTurnAnimation)
    }
}