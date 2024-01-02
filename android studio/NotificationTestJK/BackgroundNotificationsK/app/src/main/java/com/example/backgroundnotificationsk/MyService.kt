package com.example.backgroundnotificationsk

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset

class MyService : Service()
{


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {

        val handler = Handler(Looper.getMainLooper())
        val runnableLoop = kotlinx.coroutines.Runnable {
            GlobalScope.launch(Dispatchers.Main)
            {

                delay(10000)
                val sP = getSharedPreferences("mysp", Context.MODE_PRIVATE)
                val editor = sP.edit()
                editor.putString("sp6", "OK")
                editor.apply()
            }
        }
        handler.post(runnableLoop)



        return START_STICKY

    }


        //sendNotification("Пример уведомления", "Текст уведомления")

    override fun onBind(intent: Intent): IBinder?
    {

        return null
    }
}