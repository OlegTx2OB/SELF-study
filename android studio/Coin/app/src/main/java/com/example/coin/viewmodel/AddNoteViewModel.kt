package com.example.coin.viewmodel

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coin.R
import com.example.coin.data.Note
import com.example.coin.repository.room.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class AddNoteViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {

    private val _observerDatePickerB = MutableLiveData<Unit>()
    private val _observerShowToast = MutableLiveData<String>()
    private val _observerGetAmount = MutableLiveData<Unit>()

    val observerDatePickerB: LiveData<Unit> = _observerDatePickerB
    val observerShowToast: LiveData<String> = _observerShowToast
    val observerGetAmount: LiveData<Unit> = _observerGetAmount


    private val newNote = Note()

    fun setEpochDay(epochDay: Long) {
        newNote.epochDay = epochDay
    }

    fun setAmount(string: String?) {
        if (string != null && string != "") newNote.amount = string.toFloat()
    }

    fun onConfirm() {
        _observerGetAmount.value = Unit

        if (newNote.epochDay == null) newNote.epochDay = LocalDate.now().toEpochDay()

        if (newNote.amount == null) _observerShowToast.value = "enter amount"
        else if (newNote.imageName == null || newNote.categoryName == null) _observerShowToast.value =
            "choose category"
        else if (newNote.isIncomes == null) _observerShowToast.value = "choose incomes or outcomes"
        else {
            _observerShowToast.value = "all saved"
            noteRepository.insertNote(newNote)
        }
    }

    fun onCardView(view: View) {
        if (view is CardView) {
            val cardRootLayout = view.getChildAt(0) as ViewGroup

            cardRootLayout.let { rootLayout ->

                for (i in 0 until rootLayout.childCount) {
                    val childView = rootLayout.getChildAt(i)

                    if (childView is ImageView) {
                        newNote.imageName = childView.tag.toString()
                    } else if (childView is TextView) {
                        newNote.categoryName = childView.text.toString()
                    }
                }

            }
        }
    }

    fun onIncomesOutcomes(view: View) {
        newNote.isIncomes = when (view.id) {
            R.id.b_isIncomes_true -> true
            R.id.b_isIncomes_false -> false
            else -> null
        }
    }

    fun onChooseData() {
        _observerDatePickerB.value = Unit
    }
}