package com.example.coin.repository.room

import android.util.Log
import com.example.coin.R
import com.example.coin.daos.NoteDao
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository
{
    override fun ebatahah() {
        Log.d("R.id.b_isIncomes_true", "       ${R.id.b_isIncomes_true}")

    }
}