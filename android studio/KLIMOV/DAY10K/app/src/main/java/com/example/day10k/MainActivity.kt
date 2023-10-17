package com.example.day10k

import android.annotation.SuppressLint
import android.content.res.AssetFileDescriptor
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
{
    private lateinit var soundPool: SoundPool
    private var streamID = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cat: ImageView = findViewById(R.id.cat)
        val chicken: ImageView = findViewById(R.id.chicken)
        val cow: ImageView = findViewById(R.id.cow)
        val dog: ImageView = findViewById(R.id.dog)
        val duck: ImageView = findViewById(R.id.duck)
        val sheep: ImageView = findViewById(R.id.sheep)

        //проверь надо ли вообще
//        val audioAttributes = AudioAttributes.Builder()
//            .setUsage(AudioAttributes.USAGE_UNKNOWN)
//            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//            .build()

        soundPool = SoundPool.Builder()
            //.setAudioAttributes(audioAttributes)
            .build()

        val catSound = loadSound("cat.ogg")
        val chickenSound = loadSound("chicken.ogg")
        val cowSound = loadSound("cow.ogg")
        val dogSound = loadSound("dog.ogg")
        val duckSound = loadSound("duck.ogg")
        val sheepSound = loadSound("sheep.ogg")

        cat.setOnClickListener { playSound(catSound) }
        chicken.setOnClickListener { playSound(chickenSound) }
        cow.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action)
            {
                MotionEvent.ACTION_DOWN ->
                {
                    playSound(cowSound)
                    true
                }
                MotionEvent.ACTION_UP ->
                {
                    soundPool.stop(streamID)
                    view.performClick()

                    true
                }
                else -> false
            }
        }
        dog.setOnClickListener { playSound(dogSound) }
        duck.setOnClickListener { playSound(duckSound) }
        sheep.setOnClickListener { playSound(sheepSound) }

    }

    private fun loadSound(filename: String): Int
    {
        val afd: AssetFileDescriptor = try
        {
            application.assets.openFd(filename)
        }
        catch (e: Exception)
        {
            e.printStackTrace()
            return -1
        }
        return soundPool.load(afd, 2)
    }

    private fun playSound(animalSound: Int): Int
    {
        streamID = soundPool.play(animalSound, 0.7F, 0.7F, 1, 0, 1F)
        if (streamID == -1)
            Log.d("WARNING", "sound.play returned -1")
        return streamID
    }

}