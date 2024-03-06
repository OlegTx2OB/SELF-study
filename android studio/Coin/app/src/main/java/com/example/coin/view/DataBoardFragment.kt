package com.example.coin.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coin.R
import com.example.coin.databinding.FragmentDataboardBinding
import com.example.coin.repository.room.NoteRepository
import com.example.coin.viewmodel.DataBoardViewModel
import com.github.mikephil.charting.charts.PieChart
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DataBoardFragment : Fragment(R.layout.fragment_databoard) {
    private val mVM: DataBoardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDataboardBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_databoard, container, false)

        setupObservers(binding, mVM)

        setPieOptions(binding.expensesLayout.pieChart)
        setPieOptions(binding.incomesLayout.pieChart)

        return binding.root
    }

    private fun setupObservers(binding: FragmentDataboardBinding, mVM: DataBoardViewModel) {

        mVM.ldSetIncBalance.observe(viewLifecycleOwner) {
            binding.topSectionLayout.tvIncomesValue.text = it.toString()
        }

        mVM.ldSetExpBalance.observe(viewLifecycleOwner) {
            binding.topSectionLayout.tvExpensesValue.text = it.toString()
        }

        mVM.ldSetTotalBalance.observe(viewLifecycleOwner) {
            binding.topSectionLayout.tvTotalBalanceValue.text = it.toString()
        }

        mVM.ldExpTopCategoriesText.observe(viewLifecycleOwner) {
            binding.expensesLayout.imageCross.visibility = View.GONE
            binding.expensesLayout.tvTopCategories.text = it
        }

        mVM.ldIncTopCategoriesText.observe(viewLifecycleOwner) {
            binding.incomesLayout.imageCross.visibility = View.GONE
            binding.incomesLayout.tvTopCategories.text = it
        }

        mVM.ldExpPieData.observe(viewLifecycleOwner) {
            val expensesPie = binding.expensesLayout.pieChart
            expensesPie.data = it
            expensesPie.notifyDataSetChanged()
            expensesPie.invalidate()

        }

        mVM.ldIncPieData.observe(viewLifecycleOwner) {
            val incomesPie = binding.incomesLayout.pieChart
            incomesPie.data = it
            incomesPie.notifyDataSetChanged()
            incomesPie.invalidate()
        }
    }

    private fun setPieOptions(pieChart: PieChart) {
        pieChart.holeRadius = 65f
        pieChart.description.isEnabled = false
        pieChart.animateY(400)
        pieChart.legend.isEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieChart.setHoleColor(Color.TRANSPARENT)
        pieChart.setTransparentCircleAlpha(50)
        pieChart.invalidate()
    }

}