package com.example.mydatabindingtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mydatabindingtest.R

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mydatabindingtest.data.SimpleViewModel
import com.example.mydatabindingtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private val viewModel by lazy { ViewModelProvider(this).get(SimpleViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModelB = viewModel
        binding.lifecycleOwner = this
    }

}