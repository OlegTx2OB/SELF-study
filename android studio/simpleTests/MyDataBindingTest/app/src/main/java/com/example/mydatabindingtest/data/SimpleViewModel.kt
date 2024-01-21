package com.example.mydatabindingtest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SimpleViewModel : ViewModel()
{

    private val _name = MutableLiveData<String>()
    private val _lastName = MutableLiveData("Lovelace")
    private val _likes = MutableLiveData(0)

    val name: LiveData<String> = _name
    val lastName: LiveData<String> = _lastName
    val likes: LiveData<Int> = _likes


    fun onLike()
    {
        _name.value = "ds"
        _likes.value = (_likes.value ?: 0) + 1
    }
}