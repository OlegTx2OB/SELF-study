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

    fun onIncomesOutcomes(view: View, event: MotionEvent) {
        view as CardView

        val cardRootLayout = view.getChildAt(0) as ViewGroup

        val incomesTag = "incomes_card"
        val expensesTag = "expenses_card"

        val incomesCardView: CardView? = cardRootLayout.findViewWithTag(incomesTag)
        val expensesCardView: CardView? = cardRootLayout.findViewWithTag(expensesTag)

        if (incomesCardView == null || expensesCardView == null) {
            Log.e("Error", "AddNoteViewModel fun onIncomesOutcomes incomesCardView or expensesCardView not found")
        } else {
            val colorForSelectedCard = app.applicationContext.getColor(R.color.gray_200)
            val colorForNotSelectedCard = app.applicationContext.getColor(R.color.gray_800)

           // val iks = event.x
            val igrik = view.y

            val vidz = view.width
            val fds = 5
            // the click was made on the right side, where the EXPENSES button is located
            if (view.x > (view.width / 2)) {
                expensesCardView.setCardBackgroundColor(colorForSelectedCard)
                incomesCardView.setCardBackgroundColor(colorForNotSelectedCard)
                //todo доделать реакцию на нажатие
            }
            // the click was made on the left side, where the INCOMES button is located
            else {
                expensesCardView.setCardBackgroundColor(colorForNotSelectedCard)
                incomesCardView.setCardBackgroundColor(colorForSelectedCard)
            }
        }
    }

    fun onTest() { // todo убрать
    }

    fun onChooseData() {
        _observerDatePickerB.value = Unit
    }
}