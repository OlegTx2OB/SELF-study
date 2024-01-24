package com.example.coin.repository.sharedprefs

interface SharedPrefsRepository {
    fun saveData(key: String, value: String)
    fun loadData(key: String, defaultValue: String): String
    fun clearData(key: String)

}