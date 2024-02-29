package com.example.coin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coin.NoteAdapter
import com.example.coin.R
import com.example.coin.data.Note
import com.example.coin.databinding.FragmentHistoryBinding
import com.example.coin.viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(R.layout.fragment_history) {

    private val mVM: HistoryViewModel by viewModels()
    private val mAdapter: NoteAdapter = NoteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHistoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = mAdapter

            button.setOnClickListener {
                mAdapter.addNote(Note(21, "NEW note", false, 32.43f, "ic_history", 32000))
            }
        }

        return binding.root
    }

}