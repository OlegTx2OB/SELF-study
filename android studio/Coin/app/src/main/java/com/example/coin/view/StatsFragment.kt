package com.example.coin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coin.R
import com.example.coin.databinding.FragmentDataboardBinding
import com.example.coin.databinding.FragmentStatsBinding
import com.example.coin.viewmodel.StatsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatsFragment : Fragment(R.layout.fragment_stats) {
    private val mVM: StatsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentStatsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_stats, container, false)

        setViewsPresets(binding)
        setupObservers(binding, mVM)
        setupClickListeners(binding, mVM)

        return binding.root
    }

    private fun setupClickListeners(binding: FragmentStatsBinding, mVM: StatsViewModel) {

    }

    private fun setupObservers(binding: FragmentStatsBinding, mVM: StatsViewModel) {

    }

    private fun setViewsPresets(binding: FragmentStatsBinding) {

    }
}