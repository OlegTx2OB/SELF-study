package com.example.coin.view

import com.example.coin.viewmodel.SettingsViewModel
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coin.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private val mViewModel: SettingsViewModel by viewModels()
}