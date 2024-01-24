package com.example.coin.data

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var isIncomes: Boolean? = null,
    var amount: Float? = null,
    var iconColor: Int? = null,
    var imageByteArray: Drawable? = null,
    var epochDay: Long? = null
)