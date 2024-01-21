package com.example.coin.repository.room
import androidx.room.TypeConverter
import java.time.LocalDate

class Converters
{
    @TypeConverter
    fun fromLongToLocalDate(value: Long): LocalDate { return value.let { LocalDate.ofEpochDay(it) } }

    @TypeConverter
    fun fromLocalDateToLong(date: LocalDate): Long { return date.toEpochDay() }
}