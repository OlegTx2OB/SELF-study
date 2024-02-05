package com.example.coin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coin.repository.room.NoteRepository
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class DataBoardViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {

    private val _updExpPieData = MutableLiveData<PieData>()
    private val _updExpText = MutableLiveData<String>()
    private val _setIncomesText = MutableLiveData<String>()

    val updExpPieData: LiveData<PieData> = _updExpPieData
    val updExpText: LiveData<String> = _updExpText
    val setIncomesText: LiveData<String> = _setIncomesText


    private val notes = noteRepository.getAllNotes().value

    private val expPieDataSet = PieDataSet(listOf(), "ExpPie")


    fun setIncomesAmount()
    {
        val incomesNotes = notes?.filter { it.isIncomes == true }

        var value = 0f
        if(incomesNotes == null) _setIncomesText.value = value.toString()
        else {
            for (note in incomesNotes) value += note.amount!!
            _setIncomesText.value = value.toString()
        }
    }
    fun setDataExpensesPie()
    {
        val expensesNotes = notes?.filter { it.isIncomes == false }

        if (expensesNotes != null)
        {
            val totalAmountMap = hashMapOf<String, Float>()

            for (note in expensesNotes) {
                val currentTotalPrice = totalAmountMap.getOrDefault(note.categoryName, 0f)
                totalAmountMap[note.categoryName!!] = currentTotalPrice + note.amount!!
            }

            val sortedPairs = totalAmountMap
                .toList()
                .sortedByDescending { it.second }

            val entries = expPieDataSet.values.toMutableList()
            var descriptionStr = ""

            if (sortedPairs.isNotEmpty()) {
                for (i in 0 until minOf(sortedPairs.size, 3)) {
                    entries.add(PieEntry(sortedPairs[i].second, ""))

                    if (descriptionStr == "")
                        descriptionStr = "${sortedPairs[i].first}(${sortedPairs[i].second})"
                    else descriptionStr += "\n${sortedPairs[i].first}(${sortedPairs[i].second})"
                }

                if (sortedPairs.size > 3) {
                    var sum = 0f
                    for (i in 3 until sortedPairs.size) {
                        sum += sortedPairs[i].second
                    }
                    entries.add(PieEntry(sum, ""))
                    descriptionStr += "\nother($sum)"
                }
            }
            expPieDataSet.values = entries

            _updExpPieData.value = PieData(expPieDataSet)
            _updExpText.value = descriptionStr
        }


    }

    fun setDataIncomesPie()
    {

    }
}