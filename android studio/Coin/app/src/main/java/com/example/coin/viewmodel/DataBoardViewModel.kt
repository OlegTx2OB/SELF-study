package com.example.coin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coin.COLOR_ATTR_UNPRESSED_CARD
import com.example.coin.R
import com.example.coin.data.Note
import com.example.coin.getColorAttribute
import com.example.coin.repository.room.NoteRepository
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataBoardViewModel @Inject constructor(
    private val mNoteRepository: NoteRepository, private val mApp: Application
) : AndroidViewModel(mApp) {

    // Exp = Expenses, Inc = Incomes
    private val _ldExpPieData = MutableLiveData<PieData>()
    private val _ldIncPieData = MutableLiveData<PieData>()
    private val _ldExpTopCategoriesText = MutableLiveData<String>()
    private val _ldIncTopCategoriesText = MutableLiveData<String>()
    private val _ldSetExpBalance = MutableLiveData<String>()
    private val _ldSetIncBalance = MutableLiveData<String>()
    private val _ldSetTotalBalance = MutableLiveData<String>()

    val ldExpPieData: LiveData<PieData> = _ldExpPieData
    val ldIncPieData: LiveData<PieData> = _ldIncPieData
    val ldExpTopCategoriesText: LiveData<String> = _ldExpTopCategoriesText
    val ldIncTopCategoriesText: LiveData<String> = _ldIncTopCategoriesText
    val ldSetExpBalance: LiveData<String> = _ldSetExpBalance
    val ldSetIncBalance: LiveData<String> = _ldSetIncBalance
    val ldSetTotalBalance: LiveData<String> = _ldSetTotalBalance


    init {
        mNoteRepository.getAllNotes().observeForever {
            updateData(it)
        }

    }

    private fun updateData(notes: List<Note>?) {
        val incomesNotes = notes?.filter { it.isIncomes == true }
        val expensesNotes = notes?.filter { it.isIncomes == false }

        setBalances(incomesNotes, expensesNotes)

        updatePieChart(incomesNotes, true)
        updatePieChart(expensesNotes, false)
    }

    private fun setBalances(incomesNotes: List<Note>?, expensesNotes: List<Note>?) {
        var incomesBalance = 0f
        var expensesBalance = 0f
        if (incomesNotes != null) {
            for (note in incomesNotes) incomesBalance += note.amount!!
            _ldSetIncBalance.value = incomesBalance.toString()
        }
        if (expensesNotes != null) {
            for (note in expensesNotes) expensesBalance += note.amount!!
            _ldSetExpBalance.value = expensesBalance.toString()
        }
        _ldSetTotalBalance.value = "$" + (incomesBalance - expensesBalance).toString()
    }

    private fun updatePieChart(notes: List<Note>?, isIncomes: Boolean) {
        val pieDataSet = PieDataSet(listOf(), "pie")
        pieDataSet.setDrawValues(false)

        if (notes == null) {
            if (isIncomes) _ldIncPieData.value = PieData(pieDataSet)
            else _ldExpPieData.value = PieData(pieDataSet)
        } else {

            //creating Map<String, Pair<Float, Int>>, where String is Category name, Float is total sum of this category and Int is color name of background of this category
            val totalAmountMap = calculateTotalAmount(notes)
            //converting map into the list and sorting by descending by total sum
            val sortedTopCategoriesPairs = totalAmountMap.toList().sortedByDescending { it.second.first }

            //set colorArray with background colors of top categories
            val colorsList = mutableListOf<Int>()
            for (i in 0 until minOf(sortedTopCategoriesPairs.size, 4)) {
                colorsList.add(sortedTopCategoriesPairs[i].second.second)
            }
            colorsList.add(mApp.getColor(R.color.gray_600))
            pieDataSet.colors = colorsList

            //creating values which will be show in pie chart and description text with sums and category names
            val (entries, descriptionStr) = createPieChartEntries(sortedTopCategoriesPairs)
            pieDataSet.values = entries
            if (isIncomes) {
                _ldIncPieData.value = PieData(pieDataSet)
                if (descriptionStr != "") _ldIncTopCategoriesText.value = descriptionStr
            } else {
                _ldExpPieData.value = PieData(pieDataSet)
                if (descriptionStr != "") _ldExpTopCategoriesText.value = descriptionStr
            }
        }
    }

    private fun calculateTotalAmount(notes: List<Note>?): Map<String, Pair<Float, Int>> {
        val totalAmountMap = hashMapOf<String, Pair<Float, Int>>()
        for (note in notes!!) {
            val currentTotalPrice = totalAmountMap.getOrDefault(note.categoryName, 0f to 0)
            totalAmountMap[note.categoryName!!] = (currentTotalPrice.first + note.amount!!) to note.color!!
        }
        return totalAmountMap
    }

    private fun createPieChartEntries(pairs: List<Pair<String, Pair<Float, Int>>>): Pair<List<PieEntry>, String> {
        val entries = mutableListOf<PieEntry>()
        var descriptionStr = ""
        if (pairs.isNotEmpty()) {
            for (i in 0 until minOf(pairs.size, 4)) {
                entries.add(PieEntry(pairs[i].second.first, ""))
                if (descriptionStr == "") {
                    descriptionStr = "${pairs[i].second.first} - ${pairs[i].first}"
                } else {
                    descriptionStr += "\n\n${pairs[i].second.first} - ${pairs[i].first}"
                }
            }
            if (pairs.size > 4) {
                var sum = 0f
                for (i in 3 until pairs.size) {
                    sum += pairs[i].second.first
                }
                entries.add(PieEntry(sum, ""))
                descriptionStr += "\n\n$sum - other"
            }
        }
        return Pair(entries, descriptionStr)
    }
}