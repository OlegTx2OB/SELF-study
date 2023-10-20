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
        // Анимация для восхода солнца
        val sunRiseAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.sun_rise)
        // Подключаем анимацию к нужному View
        sunImageView.startAnimation(sunRiseAnimation)
    }
}