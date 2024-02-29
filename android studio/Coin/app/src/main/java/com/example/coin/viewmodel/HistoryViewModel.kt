package com.example.coin.viewmodel


import androidx.lifecycle.AndroidViewModel
import android.app.Application
import com.example.coin.repository.room.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val mNoteRepository: NoteRepository, private val mApp: Application
) : AndroidViewModel(mApp)
{

}