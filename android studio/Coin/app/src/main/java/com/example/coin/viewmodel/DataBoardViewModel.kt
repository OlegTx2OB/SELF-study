package com.example.coin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coin.R
import com.example.coin.data.Note
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
        pieDataSet.colors =
            arrayListOf(
                mApp.getColor(R.color.category_light_blue),
                mApp.getColor(R.color.category_green),
                mApp.getColor(R.color.category_orange),
                mApp.getColor(R.color.category_yellow),
            ) //todo переделать

        if (notes == null) {
            if (isIncomes) _ldIncPieData.value = PieData(pieDataSet)
            else _ldExpPieData.value = PieData(pieDataSet)
        } else {
            val totalAmountMap = calculateTotalAmount(notes)
            val sortedTopCategoriesPairs = totalAmountMap.toList().sortedByDescending { it.second }
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

    private fun calculateTotalAmount(notes: List<Note>?): Map<String, Float> {
        val totalAmountMap = hashMapOf<String, Float>()
        for (note in notes!!) {
            val currentTotalPrice = totalAmountMap.getOrDefault(note.categoryName, 0f)
            totalAmountMap[note.categoryName!!] = currentTotalPrice + note.amount!!
        }
        return totalAmountMap
    }

    private fun createPieChartEntries(sortedPairs: List<Pair<String, Float>>): Pair<List<PieEntry>, String> {
        val entries = mutableListOf<PieEntry>()
        var descriptionStr = ""
        if (sortedPairs.isNotEmpty()) {
            for (i in 0 until minOf(sortedPairs.size, 4)) {
                entries.add(PieEntry(sortedPairs[i].second, ""))
                if (descriptionStr == "") {
                    descriptionStr = "${sortedPairs[i].first} - ${sortedPairs[i].second}"
                } else {
                    descriptionStr += "\n\n${sortedPairs[i].first} - ${sortedPairs[i].second}"
                }
            }
            if (sortedPairs.size > 4) {
                var sum = 0f
                for (i in 3 until sortedPairs.size) {
                    sum += sortedPairs[i].second
                }
                entries.add(PieEntry(sum, ""))
                descriptionStr += "\n\nother - $sum"
            }
        }
        return Pair(entries, descriptionStr)
    }
}