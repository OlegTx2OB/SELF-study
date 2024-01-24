package com.example.coin.repository.room

import androidx.lifecycle.LiveData
import com.example.coin.data.Note

interface NoteRepository {
    fun getAllNotes(): LiveData<MutableList<Note>>

    fun insertNote(note: Note)

    fun deleteNote(note: Note)

}