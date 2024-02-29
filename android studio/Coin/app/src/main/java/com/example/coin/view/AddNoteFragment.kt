package com.example.coin.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.TypedValue
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
import com.example.coin.paintPressedCardView
import com.example.coin.paintUnpressedCardViews
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
            paintUnpressedCardViews(
                listOf(binding.cardviewAmountIncExp.cardviewIncomes), requireContext()
            )
            paintPressedCardView(it as CardView, requireContext())
        }

        binding.cardviewAmountIncExp.cardviewIncomes.setOnClickListener {
            mVM.onCardViewIncExp(true)
            paintUnpressedCardViews(
                listOf(binding.cardviewAmountIncExp.cardviewExpenses), requireContext()
            )
            paintPressedCardView(it as CardView, requireContext())
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
            paintUnpressedCardViews(
                listOf(
                    binding.cardviewDatepicker.cardviewYesterday,
                    binding.cardviewDatepicker.cardviewChoose,
                ), requireContext()
            )
            paintPressedCardView(it as CardView, requireContext())
        }

        binding.cardviewDatepicker.cardviewYesterday.setOnClickListener {
            mVM.setTodayMinusDays(1)
            paintUnpressedCardViews(
                listOf(
                    binding.cardviewDatepicker.cardviewToday,
                    binding.cardviewDatepicker.cardviewChoose,
                ), requireContext()
            )
            paintPressedCardView(it as CardView, requireContext())
        }

        binding.cardviewDatepicker.cardviewChoose.setOnClickListener {
            val currT = LocalDate.now()
            val datePickerDialog = DatePickerDialog(
                requireContext(), { _, y, m, d ->
                    mVM.setEpochDay(LocalDate.of(y, m + 1, d).toEpochDay())
                }, currT.year, currT.monthValue - 1, currT.dayOfMonth
            )//m+1 выбери, месяц неправильно показывается
            datePickerDialog.show()
            paintUnpressedCardViews(
                listOf(
                    binding.cardviewDatepicker.cardviewToday,
                    binding.cardviewDatepicker.cardviewYesterday,
                ), requireContext()
            )
            paintPressedCardView(it as CardView, requireContext())
        }

    }

    fun setupObservers(binding: FragmentAddNoteBinding, mVM: AddNoteViewModel) {

        mVM.ldShowToast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        mVM.ldGetAmount.observe(viewLifecycleOwner) {
            this.mVM.setAmount(binding.cardviewAmountIncExp.tilAmount.text?.toString())
        }
    }

}