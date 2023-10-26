package com.example.abstinenceapp

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.TextView
import com.example.abstinenceapp.MainActivity.Companion.isLoopActive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset

class CoroutinesMethods
{
    companion object
    {
        private lateinit var handler: Handler
        private lateinit var runnable: Runnable
        fun setClockTimeThread(context: Context,
                               mMainClockTV: TextView, activeClockRing: ProgressBar)
        {
            handler = Handler(Looper.getMainLooper())
            runnable = kotlinx.coroutines.Runnable{
                GlobalScope.launch(Dispatchers.Main)
                {
                    while (isLoopActive)
                    {
                        val currTimeInMin = (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) / 60)

                        val sP = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
                        val sPTimeInMin = sP.getLong("savedTime", currTimeInMin)
                        if (sPTimeInMin == currTimeInMin) TimeMethods.putLongToSP(context, "savedTime", currTimeInMin)

                        mMainClockTV.text = TimeMethods.setTimeToMainClock(currTimeInMin, sPTimeInMin)
                        activeClockRing.progress = ((currTimeInMin - sPTimeInMin) % 1440).toInt()
                        delay(2000)
                        }
                    }
                }
            handler.post(runnable)
        }


    }
}