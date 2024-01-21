package com.example.coin.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val isIncomes: Boolean,
    val amount: Float,
    val iconColor: Int,
    val imagePath: String,
    val localDate: LocalDate
)