package com.example.notificationtestk

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat



class MainActivity : AppCompatActivity()
{
    lateinit var textView : TextView
    val notificationId = 0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
        setNotificationTextOnTextView()
        cancelNotification()
    }

    private fun createNotificationChannel()
    {
        val channel = NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
    private fun cancelNotification()
    {
        val notificationManager = NotificationManagerCompat.from(this);
        notificationManager.cancel(notificationId);
    }
    private fun setNotificationTextOnTextView()
    {
        textView = findViewById(R.id.textView)
        val text = intent.getStringExtra("text")
        text?.let{ textView.text = text }
    }
    fun onClickNotificationButton(view : View)
    {
        val yesIntent = Intent(this, MainActivity::class.java)
        val noIntent = Intent(this, MainActivity::class.java)

        yesIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .putExtra("text", "Yes")
        noIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .putExtra("text", "No")

        val yesPendingIntent = PendingIntent.getActivity(this, 1, yesIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_UPDATE_CURRENT)
        val noPendingIntent = PendingIntent.getActivity(this, 2, noIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_UPDATE_CURRENT)

        val yesBuilder = NotificationCompat.Action.Builder(R.drawable.yes_icon, "Yes", yesPendingIntent).build()
        val noBuilder = NotificationCompat.Action.Builder(R.drawable.no_icon, "No", noPendingIntent).build()


        val notificationIntent = packageManager.getLaunchIntentForPackage(packageName)
        notificationIntent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val builder = NotificationCompat.Builder(this, "default")
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.content_title))
            .setContentText(getString(R.string.content_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(yesBuilder)
            .addAction(noBuilder)
            .setAutoCancel(false)

        val notificationManagerCompat = NotificationManagerCompat.from(this)


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
            == PackageManager.PERMISSION_GRANTED
        ) notificationManagerCompat.notify(notificationId, builder.build())
    }
}