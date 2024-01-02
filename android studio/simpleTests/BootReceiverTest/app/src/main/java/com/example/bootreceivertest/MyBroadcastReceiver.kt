package com.example.backgroundnotificationsk

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class MyBroadcastReceiver : BroadcastReceiver()
{
    override fun onReceive(context: Context, intent: Intent)
    {
        val serviceIntent = Intent(context, MyService::class.java)
        context.startService(serviceIntent)
    }
}