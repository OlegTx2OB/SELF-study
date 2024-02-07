package com.example.coin.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.coin.daos.NoteDao
import com.example.coin.NotesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext context: Context): NoteDao {
        Log.d("testAllNotes", "1room module ok")

        return Room.databaseBuilder(
            context,
            NotesDataBase::class.java, "roomDBNotes"
        )
            .build()
            .notesDao()
    }
}