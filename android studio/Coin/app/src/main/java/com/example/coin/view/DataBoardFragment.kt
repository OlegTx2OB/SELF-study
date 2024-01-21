package com.example.coin.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coin.R
import com.example.coin.viewmodel.DataBoardViewModel


class DataBoardFragment : Fragment(R.layout.fragment_databoard)
{
    private val mViewModel by lazy { ViewModelProvider(this).get(DataBoardViewModel::class.java) }

}