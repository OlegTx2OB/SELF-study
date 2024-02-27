package com.example.coin.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
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
    private val mVM: AddNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentAddNoteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)

        setupClickListeners(binding, mVM)
        setupObservers(binding, mVM)

        return binding.root
    }

    fun setupClickListeners(binding: FragmentAddNoteBinding, mVM: AddNoteViewModel) {

        binding.cardviewAmountIncExp.cardviewExpenses.setOnClickListener {
            mVM.onCardViewIncExp(false)
            mVM.paintUnpressedCardViews(listOf(binding.cardviewAmountIncExp.cardviewIncomes))
            mVM.paintPressedCardView(it as CardView)

        }

        binding.cardviewAmountIncExp.cardviewIncomes.setOnClickListener {
            mVM.onCardViewIncExp(true)
            mVM.paintUnpressedCardViews(listOf(binding.cardviewAmountIncExp.cardviewExpenses))
            mVM.paintPressedCardView(it as CardView)
        }

    }

    fun setupObservers(binding: FragmentAddNoteBinding, mVM: AddNoteViewModel) {

        this.mVM.ldShowToast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        this.mVM.ldGetAmount.observe(viewLifecycleOwner) {
            //this.mVM.setAmount(binding.tilAmount.text?.toString()) todo поправить
        }
    }

}