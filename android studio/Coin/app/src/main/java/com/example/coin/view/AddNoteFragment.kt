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
import com.example.coin.paintCardViews
import com.example.coin.viewmodel.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class AddNoteFragment : Fragment(R.layout.fragment_add_note) {
    private val mVM: AddNoteViewModel by viewModels()
    private val colorUnpressedCard = com.google.android.material.R.attr.colorSurfaceContainerHighest
    private val colorPressedCard = com.google.android.material.R.attr.colorPrimaryContainer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentAddNoteBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)

        setupClickListeners(binding, mVM)
        setupObservers(binding, mVM)

        return binding.root
    }

    private fun setupClickListeners(binding: FragmentAddNoteBinding, mVM: AddNoteViewModel) {

        binding.cardviewAmountIncExp.cardviewExpenses.setOnClickListener {
            mVM.onCardViewIncExp(false)
            paintCardViews(
                listOf(binding.cardviewAmountIncExp.cardviewIncomes),
                colorUnpressedCard,
                requireContext()
            )
            paintCardViews(listOf(it as CardView), colorPressedCard, requireContext())
        }
        binding.cardviewAmountIncExp.cardviewIncomes.setOnClickListener {
            mVM.onCardViewIncExp(true)
            paintCardViews(
                listOf(binding.cardviewAmountIncExp.cardviewExpenses),
                colorUnpressedCard,
                requireContext()
            )
            paintCardViews(listOf(it as CardView), colorPressedCard, requireContext())
        }

        binding.cardviewAdd.setOnClickListener {
            mVM.onAdd()
        }

        binding.cardviewsCategories.cardCategoryShlukhi.setOnClickListener {
            mVM.onCategory(it)
        }

        binding.cardviewsCategories.cardCategoryShawarma.setOnClickListener {
            mVM.onCategory(it)
        }

        binding.cardviewsCategories.cardCategoryTaxi.setOnClickListener {
            mVM.onCategory(it)
        }

        binding.cardviewDatepicker.cardviewToday.setOnClickListener {
            mVM.setTodayMinusDays(0)
            paintCardViews(
                listOf(
                    binding.cardviewDatepicker.cardviewYesterday,
                    binding.cardviewDatepicker.cardviewChoose,
                ), colorUnpressedCard, requireContext()
            )
            paintCardViews(listOf(it as CardView), colorPressedCard, requireContext())
        }

        binding.cardviewDatepicker.cardviewYesterday.setOnClickListener {
            mVM.setTodayMinusDays(1)
            paintCardViews(
                listOf(
                    binding.cardviewDatepicker.cardviewToday,
                    binding.cardviewDatepicker.cardviewChoose,
                ), colorUnpressedCard, requireContext()
            )
            paintCardViews(listOf(it as CardView), colorPressedCard, requireContext())
        }

        binding.cardviewDatepicker.cardviewChoose.setOnClickListener {
            val currT = LocalDate.now()
            val datePickerDialog = DatePickerDialog(
                requireContext(), { _, y, m, d ->
                    mVM.setEpochDay(LocalDate.of(y, m + 1, d).toEpochDay())
                }, currT.year, currT.monthValue - 1, currT.dayOfMonth
            )//m+1 выбери, месяц неправильно показывается
            datePickerDialog.show()
            paintCardViews(
                listOf(
                    binding.cardviewDatepicker.cardviewToday,
                    binding.cardviewDatepicker.cardviewYesterday,
                ), colorUnpressedCard, requireContext()
            )
            paintCardViews(listOf(it as CardView), colorPressedCard, requireContext())
        }

    }

    private fun setupObservers(binding: FragmentAddNoteBinding, mVM: AddNoteViewModel) {

        mVM.ldShowToast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        mVM.ldGetAmount.observe(viewLifecycleOwner) {
            this.mVM.setAmount(binding.cardviewAmountIncExp.tilAmount.text?.toString())
        }
    }

}