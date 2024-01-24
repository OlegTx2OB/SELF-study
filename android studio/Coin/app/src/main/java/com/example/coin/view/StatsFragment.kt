package com.example.coin.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coin.R
import com.example.coin.viewmodel.StatsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatsFragment : Fragment(R.layout.fragment_stats) {
    private val mViewModel: StatsViewModel by viewModels()
}