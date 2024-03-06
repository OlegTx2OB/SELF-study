package com.example.coin.repository.sharedprefs

import android.content.Context
fun spGetCurrencyName(context: Context) : String
{
    val sP = context.getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE)
    return sP.getString("currency_name", "$") ?: "$"
}

fun spSaveCurrencyName(value: String, context: Context)
{
    val sP = context.getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE)
    val editor = sP.edit()
    editor.putString("currency_name", value)
    editor.apply()
}