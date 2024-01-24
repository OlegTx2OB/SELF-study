package com.example.coin

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.room.TypeConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class Converters @Inject constructor(@ApplicationContext private val context: Context) {
    @TypeConverter
    fun fromDrawable(drawable: Drawable): ByteArray {
        val bitmap = (drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    @TypeConverter
    fun toDrawable(byteArray: ByteArray): Drawable {
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return BitmapDrawable(context.resources, bitmap)
    }
}