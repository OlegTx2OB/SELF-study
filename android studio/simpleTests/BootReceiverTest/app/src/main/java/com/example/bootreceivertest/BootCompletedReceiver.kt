package com.example.bootreceivertest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast




class BootCompletedReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context, intent: Intent)
    {
        val message = ("Обнаружено сообщение " + intent.action)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()


    }

}