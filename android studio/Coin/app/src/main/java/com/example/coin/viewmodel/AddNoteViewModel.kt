package com.example.coin.viewmodel

import android.app.Application
import android.app.DatePickerDialog
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
    private val mNoteRepository: NoteRepository,
    private val mApp: Application,
) : AndroidViewModel(mApp) {

    private val _ldShowToast = MutableLiveData<String>()
    private val _ldGetAmount = MutableLiveData<Unit>()

    val ldShowToast: LiveData<String> = _ldShowToast
    val ldGetAmount: LiveData<Unit> = _ldGetAmount

    private val mNewNote = Note()

    private val mGray800 = mApp.getColor(R.color.gray_800)
    private val mGray400 = mApp.getColor(R.color.gray_400)

    fun onCardViewIncExp(isIncomes: Boolean) {
        mNewNote.isIncomes = isIncomes
    }

    fun paintPressedCardView(cardView: CardView) {
        cardView.setCardBackgroundColor(mGray400)
    }

    fun paintUnpressedCardViews(viewList: List<CardView>) {
        for (cardView in viewList) {
            cardView.setCardBackgroundColor(mGray800)
        }
    }


    fun setEpochDay(epochDay: Long) {
        mNewNote.epochDay = epochDay
    }

    fun setAmount(string: String?) {
        if (string != null && string != "") mNewNote.amount = string.toFloat()
    }

    fun onConfirm() {
        _ldGetAmount.value = Unit

        if (mNewNote.epochDay == null) mNewNote.epochDay = LocalDate.now().toEpochDay()

        if (mNewNote.amount == null) _ldShowToast.value = "enter amount"
        else if (mNewNote.imageName == null || mNewNote.categoryName == null) _ldShowToast.value =
            "choose category"
        else if (mNewNote.isIncomes == null) _ldShowToast.value = "choose incomes or outcomes"
        else {
            _ldShowToast.value = "all saved"
            mNoteRepository.insertNote(mNewNote)
        }
    }

    fun onCategory(view: View) {
        view as CardView

        val cardRootLayout = view.getChildAt(0) as ViewGroup

        for (i in 0 until cardRootLayout.childCount) {
            val childView = cardRootLayout.getChildAt(i)

            if (childView is ImageView) {
                mNewNote.imageName = childView.tag.toString()
            } else if (childView is TextView) {
                mNewNote.categoryName = childView.text.toString()
            }
        }

    }

    fun onChooseData() {
        val currT = LocalDate.now()
        val datePickerDialog = DatePickerDialog(
            mApp.applicationContext, { _, y, m, d ->
                mNewNote.epochDay = LocalDate.of(y, m + 1, d).toEpochDay()
            }, currT.year, currT.monthValue - 1, currT.dayOfMonth
        )//m+1 выбери, месяц неправильно показывается
        datePickerDialog.show()
    }

    fun onTodayMinusDays(daysCount: Int) {
        mNewNote.epochDay = LocalDate.now().toEpochDay() - daysCount
    }
}