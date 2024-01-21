package com.example.coin.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coin.R
import com.example.coin.viewmodel.StatsViewModel

class StatsFragment : Fragment(R.layout.fragment_stats)
{
    private val mViewModel by lazy { ViewModelProvider(this).get(StatsViewModel::class.java) }
}