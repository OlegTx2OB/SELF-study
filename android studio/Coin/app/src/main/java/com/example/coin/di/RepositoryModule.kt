package com.example.coin.di

import android.util.Log
import com.example.coin.repository.room.NoteRepository
import com.example.coin.repository.room.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideNoteRepository(noteRepositoryImpl: NoteRepositoryImpl): NoteRepository {
        Log.d("testAllNotes", "2repository module ok")
        return noteRepositoryImpl
    }
}