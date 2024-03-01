package com.example.coin.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coin.NoteAdapter
import com.example.coin.R
import com.example.coin.data.Note
import com.example.coin.databinding.FragmentAddNoteBinding
import com.example.coin.databinding.FragmentHistoryBinding
import com.example.coin.repository.room.NoteRepository
import com.example.coin.repository.room.NoteRepositoryImpl
import com.example.coin.viewmodel.AddNoteViewModel
import com.example.coin.viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {
    @Inject
    lateinit var mNoteRepository: NoteRepository

    private val mVM: HistoryViewModel by viewModels()
    private val mAdapter: NoteAdapter = NoteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHistoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = mAdapter
        setupObservers(binding, mVM)

        return binding.root
    }



    fun setupObservers(binding: FragmentHistoryBinding, mVM: HistoryViewModel) {4

        mNoteRepository.getAllNotes().observe(viewLifecycleOwner) {
            mAdapter.updateData(it)
        }
    }
}