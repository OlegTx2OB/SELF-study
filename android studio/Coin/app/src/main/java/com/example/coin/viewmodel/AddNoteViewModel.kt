package com.example.coin.viewmodel

import android.app.Application
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coin.R
import com.example.coin.data.Note
import com.example.coin.repository.room.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val app: Application,
) :
    AndroidViewModel(app) {

    private val _observerDatePickerB = MutableLiveData<Unit>()
    private val _observerShowToast = MutableLiveData<String>()
    private val _observerGetAmount = MutableLiveData<Unit>()

    val observerDatePickerB: LiveData<Unit> = _observerDatePickerB
    val observerShowToast: LiveData<String> = _observerShowToast
    val observerGetAmount: LiveData<Unit> = _observerGetAmount

    private val newNote = Note()

    private val gray800 = app.getColor(R.color.gray_800)
    private val gray200 = app.getColor(R.color.gray_200)

    fun onCardViewIncExp(isIncomes: Boolean) {
        newNote.isIncomes = isIncomes
    }

    fun paintPressedCardView(cardView: CardView) {
        cardView.setCardBackgroundColor(gray200)
        val rootLayout = cardView.getChildAt(0) as ViewGroup

        for (i in 0 until rootLayout.childCount) {
            val childView = rootLayout.getChildAt(i)
            if (childView is TextView) {
                childView.setTextColor(gray800)
            }
        }
    }

    fun paintUnpressedCardViews(viewList: List<CardView>) {

    }






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

    fun onCategory(view: View) {
        view as CardView

        val cardRootLayout = view.getChildAt(0) as ViewGroup

        for (i in 0 until cardRootLayout.childCount) {
            val childView = cardRootLayout.getChildAt(i)

            if (childView is ImageView) {
                newNote.imageName = childView.tag.toString()
            } else if (childView is TextView) {
                newNote.categoryName = childView.text.toString()
            }
        }

    }

    fun onChooseData() {
        _observerDatePickerB.value = Unit
    }
}