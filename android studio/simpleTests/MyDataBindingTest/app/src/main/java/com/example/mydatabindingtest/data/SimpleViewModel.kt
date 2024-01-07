package com.example.mydatabindingtest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SimpleViewModel : ViewModel() {

    private val _name = MutableLiveData("Ada")
    private val _lastName = MutableLiveData("Lovelace")
    private val _likes = MutableLiveData(0)


    val name: LiveData<String> = _name
    val lastName: LiveData<String> = _lastName
    val likes: LiveData<Int> = _likes

    // popularity is exposed as LiveData using a Transformation instead of a @Bindable property.
    val popularity = MediatorLiveData<Popularity>().apply {
        addSource(_likes) { likes ->
            value = when {
                likes > 9 -> Popularity.STAR
                likes > 4 -> Popularity.POPULAR
                else -> Popularity.NORMAL
            }
        }
    }

    fun onLike() {
        _likes.value = (_likes.value ?: 0) + 1
    }
}

enum class Popularity
{
    NORMAL,
    POPULAR,
    STAR
}