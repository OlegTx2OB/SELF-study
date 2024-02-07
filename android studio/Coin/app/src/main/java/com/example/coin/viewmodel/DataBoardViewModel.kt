package com.example.coin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coin.data.Note
import com.example.coin.repository.room.NoteRepository
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class DataBoardViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {

    private val _expensesPieData = MutableLiveData<PieData>()
    private val _incomesPieData = MutableLiveData<PieData>()
    private val _expensesTopCategoriesText = MutableLiveData<String>()
    private val _incomesTopCategoriesText = MutableLiveData<String>()
    private val _setExpensesBalance = MutableLiveData<String>()
    private val _setIncomesBalance = MutableLiveData<String>()
    private val _setTotalBalance = MutableLiveData<String>()

    val expensesPieData: LiveData<PieData> = _expensesPieData
    val incomesPieData: LiveData<PieData> = _incomesPieData
    val expensesTopCategoriesText: LiveData<String> = _expensesTopCategoriesText
    val incomesTopCategoriesText: LiveData<String> = _expensesTopCategoriesText
    val setExpensesBalance: LiveData<String> = _setExpensesBalance
    val setIncomesBalance: LiveData<String> = _setIncomesBalance
    val setTotalBalance: LiveData<String> = _setTotalBalance

    private val notes = noteRepository.getAllNotes().value

    fun setEntranceValues()//todo мб потом название сменить
    {
        val incomesNotes = notes?.filter { it.isIncomes == true }
        val expensesNotes = notes?.filter { it.isIncomes == false }
        setBalances(incomesNotes, expensesNotes)
        updatePieChart(incomesNotes, true)
        updatePieChart(expensesNotes, false)
    }
    private fun setBalances(incomesNotes: List<Note>?, expensesNotes: List<Note>?)
    {
        var incomesBalance = 0f
        var expensesBalance = 0f
        if(incomesNotes != null) {
            for (note in incomesNotes) incomesBalance += note.amount!!
            _setIncomesBalance.value = incomesBalance.toString()
        }
        if(expensesNotes != null) {
            for (note in expensesNotes) expensesBalance += note.amount!!
            _setExpensesBalance.value = expensesBalance.toString()
        }
        if(incomesBalance != 0f && expensesBalance != 0f) {
            _setTotalBalance.value = (incomesBalance + expensesBalance).toString()
        }
    }

    private fun updatePieChart(notes: List<Note>?, isIncomes: Boolean) {
        val pieDataSet = PieDataSet(listOf(), "pie")
        if (notes == null) {
            if (isIncomes) _incomesPieData.value = PieData(pieDataSet)
            else _expensesPieData.value = PieData(pieDataSet)
        }
        else {
            val totalAmountMap = calculateTotalAmount(notes)
            val sortedTopCategoriesPairs = totalAmountMap.toList().sortedByDescending { it.second }
            val (entries, descriptionStr) = createPieChartEntries(sortedTopCategoriesPairs)
            pieDataSet.values = entries

            if (isIncomes) {
                _incomesPieData.value = PieData(pieDataSet)
                _incomesTopCategoriesText.value = descriptionStr
            }
            else {
                _expensesPieData.value = PieData(pieDataSet)
                _expensesTopCategoriesText.value = descriptionStr
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
            for (i in 0 until minOf(sortedPairs.size, 3)) {
                entries.add(PieEntry(sortedPairs[i].second, ""))
                if (descriptionStr == "") {
                    descriptionStr = "${sortedPairs[i].first}(${sortedPairs[i].second})"
                } else {
                    descriptionStr += "\n\n${sortedPairs[i].first}(${sortedPairs[i].second})"
                }
            }
            if (sortedPairs.size > 3) {
                var sum = 0f
                for (i in 3 until sortedPairs.size) {
                    sum += sortedPairs[i].second
                }
                entries.add(PieEntry(sum, ""))
                descriptionStr += "\n\nother($sum)"
            }
        }
        return Pair(entries, descriptionStr)
    }
}