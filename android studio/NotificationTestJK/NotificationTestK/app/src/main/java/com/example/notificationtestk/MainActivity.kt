package com.example.notificationtestk

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
    }

    private fun createNotificationChannel()
    {
        val channel = NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
    fun onClickNotificationButton(view : View)
    {
        val yesIntent = Intent(this, MainActivity::class.java)
        val noIntent = Intent(this, MainActivity::class.java)
        yesIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        noIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        yesIntent.putExtra("response", "Yes")
        noIntent.putExtra("response", "No")
        val yesPendingIntent = PendingIntent.getActivity(this, 1, yesIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_UPDATE_CURRENT)
        val noPendingIntent = PendingIntent.getActivity(this, 2, noIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_UPDATE_CURRENT)

        val yesBuilder = NotificationCompat.Action.Builder(R.drawable.yes_icon, "Yes", yesPendingIntent).build()
        val noBuilder = NotificationCompat.Action.Builder(R.drawable.no_icon, "No", noPendingIntent).build()


        val notificationIntent = packageManager.getLaunchIntentForPackage(packageName)
        notificationIntent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, "default")
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.content_title))
            .setContentText(getString(R.string.content_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(yesBuilder)
            .addAction(noBuilder)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManagerCompat = NotificationManagerCompat.from(this)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
            == PackageManager.PERMISSION_GRANTED
        ) notificationManagerCompat.notify(0, builder.build())
    }
}