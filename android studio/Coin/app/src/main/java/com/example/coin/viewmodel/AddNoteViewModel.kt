package com.example.coin.viewmodel

import android.app.Application
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coin.data.Note
import com.example.coin.repository.room.CategoryRepository
import com.example.coin.repository.room.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val mNoteRepository: NoteRepository,
    private val mCategoryRepository: CategoryRepository,
    private val mApp: Application,
) : AndroidViewModel(mApp) {

    private val _ldGetAmount = MutableLiveData<Unit>()
    val ldGetAmount: LiveData<Unit> = _ldGetAmount

    private val mNewNote = Note()

    fun onCardViewIncExp(isIncomes: Boolean) {
        mNewNote.isIncomes = isIncomes
    }

    fun setAmount(string: String?) {
        if (string != null && string != "") mNewNote.amount = string.toFloat()
    }

    fun onAdd(): String{
        _ldGetAmount.value = Unit

        val todayEpochDay = LocalDate.now().toEpochDay()
        if (mNewNote.epochDay == null) {
            mNewNote.epochDay = todayEpochDay
        }

        return if (mNewNote.amount == null) {
            "enter amount"
        } else if (mNewNote.isIncomes == null) {
            "choose incomes or outcomes"
        } else if (mNewNote.imageName == null || mNewNote.categoryName == null || mNewNote.color == null) {
            "choose category"
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                mNoteRepository.insertNote(mNewNote)
            }
            "all saved"
        }
    }

    fun onCategory(view: View) {
        view as CardView
        val cardRootLayout = view.getChildAt(0) as ViewGroup

        for (i in 0 until cardRootLayout.childCount) {
            val childView = cardRootLayout.getChildAt(i)

            if (childView is CardView) {
               mNewNote.color = childView.cardBackgroundColor.defaultColor
                val imageView = childView.getChildAt(0) as ImageView
                mNewNote.imageName = imageView.tag.toString()
            } else if (childView is TextView) {
                mNewNote.categoryName = childView.text.toString()
            }
        }

    }

    fun setTodayMinusDays(daysCount: Int) {
        mNewNote.epochDay = LocalDate.now().toEpochDay() - daysCount
    }

    fun setEpochDay(daysCount: Long) {
        mNewNote.epochDay = daysCount
    }
}

//// Получаем цвет фона CardView
//val typedValue = TypedValue()
//context.theme.resolveAttribute(R.attr.cardBackgroundColor, typedValue, true)
//val colorCardView = typedValue.data
//
//// Добавляем 50% альфа-канал к цвету фона
//val alphaColor = ColorUtils.setAlphaComponent(colorCardView, (255 * 0.5).toInt())
//
//// Устанавливаем новый цвет с альфа-каналом для CardView
//cardView.setCardBackgroundColor(alphaColor)