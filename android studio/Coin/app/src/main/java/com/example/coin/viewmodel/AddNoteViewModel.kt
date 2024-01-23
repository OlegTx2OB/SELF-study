package com.example.coin.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.coin.R
import com.example.coin.repository.room.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel()
{
    fun onConfirm() {

    }
    fun onIcon(view: View) {}
    fun onIncomesOutcomes() {}
    fun onChooseData() {}
}