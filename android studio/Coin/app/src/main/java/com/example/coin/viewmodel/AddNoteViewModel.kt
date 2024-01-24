package com.example.coin.viewmodel

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coin.R
import com.example.coin.data.Note
import com.example.coin.repository.room.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddNoteViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {

    private val _observerConfirmB: MutableLiveData<Unit> = MutableLiveData<Unit>()
    val observerConfirmB: LiveData<Unit> = _observerConfirmB

    private val newNote = Note()
    fun onConfirm() {
//        //todo fix stub
//        newNote.amount = 8008.5f
//        noteRepository.insertNote(newNote)

        _observerConfirmB.value = Unit

    }

    fun onIcon(view: View) {
        //todo fix stub
        view as ImageView

        newNote.imageByteArray = view.drawable

        val colorFilter: ColorFilter = PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN) // Создание ColorFilter из цвета

        view.colorFilter = colorFilter

    }

    fun onIncomesOutcomes(view: View) {
        newNote.isIncomes = when (view.id) {
            R.id.b_isIncomes_true -> true
            R.id.b_isIncomes_false -> false
            else -> null
        }
    }

    fun onChooseData() {
        //todo fix stub


    }
}