package com.example.coin.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coin.R
import com.example.coin.databinding.FragmentAddNoteBinding
import com.example.coin.viewmodel.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class AddNoteFragment : Fragment(R.layout.fragment_add_note) {
    private val mViewModel: AddNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentAddNoteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        binding.viewModel = mViewModel

        mViewModel.observerDatePickerB.observe(viewLifecycleOwner) {

            val currT = LocalDate.now()
            val datePickerDialog = DatePickerDialog(requireContext(), { _, y, m, d ->
                mViewModel.setEpochDay(LocalDate.of(y, m + 1, d).toEpochDay())
            }, currT.year, currT.monthValue - 1, currT.dayOfMonth)//m+1 выбери, месяц неправильно показывается
            datePickerDialog.show()
        }

        mViewModel.observerShowToast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        mViewModel.observerGetAmount.observe(viewLifecycleOwner){
            mViewModel.setAmount(binding.tilAmount.text?.toString())
        }

        return binding.root
    }
}