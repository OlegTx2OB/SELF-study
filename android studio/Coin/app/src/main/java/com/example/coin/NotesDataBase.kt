package com.example.coin

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coin.daos.NoteDao
import com.example.coin.data.Note

@Database(entities = [Note::class], version = 1)
abstract class NotesDataBase : RoomDatabase()
{
    abstract fun notesDao(): NoteDao
}