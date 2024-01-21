package com.example.coin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coin.R
import com.example.coin.databinding.FragmentAddNoteBinding
import com.example.coin.viewmodel.AddNoteViewModel

class AddNoteFragment : Fragment(R.layout.fragment_add_note)
{

    private val mViewModel by lazy { ViewModelProvider(this).get(AddNoteViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        val binding: FragmentAddNoteBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_add_note, container, false)
        binding.viewModel = mViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


}