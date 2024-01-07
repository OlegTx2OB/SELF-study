package com.example.mydatabindingtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mydatabindingtest.R

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mydatabindingtest.data.Popularity
import com.example.mydatabindingtest.data.SimpleViewModel
import com.example.mydatabindingtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{

    // Obtain ViewModel from ViewModelProviders
    private val viewModel by lazy { ViewModelProviders.of(this).get(SimpleViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModelB = viewModel
        binding.lifecycleOwner = this
    }

}