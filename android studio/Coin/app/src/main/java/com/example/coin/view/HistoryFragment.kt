package com.example.coin.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coin.R
import com.example.coin.viewmodel.HistoryViewModel

class HistoryFragment : Fragment(R.layout.fragment_history)
{
    private val mViewModel by lazy { ViewModelProvider(this).get(HistoryViewModel::class.java) }


}