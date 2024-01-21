package com.example.coin.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import java.time.LocalDate


class AddNoteViewModel : ViewModel()
{
//    private val _isIncomes = MutableLiveData<Boolean>()
//    private val _amount = MutableLiveData<Float>()
//    private val _iconColor = MutableLiveData<Int>()
//    private val _imagePath = MutableLiveData<String>()
//    private val _localDate = MutableLiveData<LocalDate>()
//
//    val isIncomes: LiveData<Boolean> = _isIncomes
//    val amount: LiveData<Float> = _amount
//    val iconColor: LiveData<Int> = _iconColor
//    val imagePath: LiveData<String> = _imagePath
//    val localDate: LiveData<LocalDate> = _localDate

    var isIncomes: Boolean? = null
    var amount: Float? = null
    var iconColor: Int? = null
    var imagePath: String? = null
    var localDate: LocalDate? = null

    fun onConfirm()
    {
        //todo цвет, кол-во грошив, isIncomes беру как константу, потом надо будет разобраться с этим
        iconColor = 0x0FF0
        amount = 666.5f



    }
    fun onIcon(view: View)
    {

    }


    fun onIncomes()
    {
        isIncomes = true
    }

    fun onOutcomes()
    {
        isIncomes = false
    }

    fun onChooseData()
    {
        localDate = LocalDate.of(2005, 0 + 1, 5)
    }
}