package com.example.abstinenceapp

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.TextView
import com.example.abstinenceapp.MainActivity.Companion.isLoopActive
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.getLongSP
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.getStringSP
import com.example.abstinenceapp.SharedPreferencesMethods.Companion.saveLongSP
import com.example.abstinenceapp.TimeMethods.Companion.getTimeForMainClockTV
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
        fun newThreadCheckAndSetTime(context: Context,
                                     mMainClockTV: TextView, activeClockRing: ProgressBar)
        {
            handler = Handler(Looper.getMainLooper())
            runnable = kotlinx.coroutines.Runnable{
                GlobalScope.launch(Dispatchers.Main)
                {
                    while (isLoopActive)
                    {
                        val currEpochMinute = LocalDateTime.now()
                            .toEpochSecond(ZoneOffset.UTC) / 60

                        val appMode = getStringSP(context, "savedAppMode", "smoking")
                        val savedEpochMinute = getLongSP(context, "savedTime$appMode", currEpochMinute)
                        if(currEpochMinute == savedEpochMinute)
                            saveLongSP(context, "savedTime$appMode", currEpochMinute)

                        mMainClockTV.text = getTimeForMainClockTV(currEpochMinute - savedEpochMinute)
                        activeClockRing.progress = ((currEpochMinute - savedEpochMinute) % 1440).toInt()
                        delay(200)
                    }
                }
            }
            handler.post(runnable)
        }


    }
}