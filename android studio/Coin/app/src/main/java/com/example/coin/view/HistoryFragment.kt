package com.example.coin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coin.NoteAdapter
import com.example.coin.R
import com.example.coin.SwipeToDeleteCallback
import com.example.coin.databinding.FragmentHistoryBinding
import com.example.coin.repository.room.NoteRepository
import com.example.coin.viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.recyclerView.adapter as NoteAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        setupObservers(binding, mVM)

        return binding.root
    }



    fun setupObservers(binding: FragmentHistoryBinding, mVM: HistoryViewModel) {

        mNoteRepository.getAllNotes().observe(viewLifecycleOwner) {
            mAdapter.addNotes(it)
        }

        mAdapter.ldDeleteNoteFromRoom.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.IO).launch {
                mNoteRepository.deleteNote(it)
            }
        }
    }
}