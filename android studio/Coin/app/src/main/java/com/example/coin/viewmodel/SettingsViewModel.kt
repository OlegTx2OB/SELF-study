package com.example.coin.viewmodel

import androidx.lifecycle.ViewModel
import com.example.coin.repository.room.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {
    // TODO: Implement the ViewModel
}