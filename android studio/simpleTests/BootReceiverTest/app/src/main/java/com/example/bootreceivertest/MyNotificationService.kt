package com.example.bootreceivertest

import android.app.Service
import android.os.IBinder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyNotificationService : Service()
{
    private val channelId = "channel_id"
    private val channelName = "Channel Name"
    private val notificationId = 1

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        showNotification()
        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder?
    {
        TODO("Not yet implemented")
    }

    private fun showNotification()
    {
        createNotificationChannel()

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Если разрешение не предоставлено, запросите его у пользователя
            // Здесь вы можете использовать ваш собственный код для запроса разрешения
        }
        else
        {
            val notificationIntent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Notification Title")
                .setContentText("Notification Text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)) {
                notify(notificationId, builder.build())
            }
        }
    }

    private fun createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Channel Description"
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}