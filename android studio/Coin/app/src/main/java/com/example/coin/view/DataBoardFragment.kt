package com.example.coin.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coin.R
import com.example.coin.viewmodel.DataBoardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataBoardFragment : Fragment(R.layout.fragment_databoard)
{
    private val mViewModel: DataBoardViewModel by viewModels()

}