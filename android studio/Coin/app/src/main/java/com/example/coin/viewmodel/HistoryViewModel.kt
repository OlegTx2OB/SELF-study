package com.example.coin.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.coin.NotesDataBase
import com.example.coin.repository.room.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class HistoryViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {
    // TODO: Implement the ViewModel
}