package com.example.coin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.coin.repository.room.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val mNoteRepository: NoteRepository,
    private val mApp: Application,
) : AndroidViewModel(mApp) {
    fun onSetCategoryColor() {
        TODO("Not yet implemented")
    }

}