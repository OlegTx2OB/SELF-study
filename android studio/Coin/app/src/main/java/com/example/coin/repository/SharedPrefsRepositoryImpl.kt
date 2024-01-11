package com.example.coin.repository
import android.content.SharedPreferences

class SharedPrefsRepositoryImpl(private val sharedPreferences: SharedPreferences)
{
    fun saveData(key: String, value: String)
    {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun loadData(key: String, defaultValue: String): String
    {
        //почему-то без элвиса не работает, мб джавовские заморочки
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun clearData(key: String)
    {
        sharedPreferences.edit().remove(key).apply()
    }
}