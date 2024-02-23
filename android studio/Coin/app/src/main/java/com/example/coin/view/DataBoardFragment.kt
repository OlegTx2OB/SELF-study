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
    private val mViewModel: DataBoardViewModel by viewModels()

    @Inject
    lateinit var noteRepository: NoteRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDataboardBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_databoard, container, false)
        binding.viewModel = mViewModel

//        noteRepository.getAllNotes().observe(viewLifecycleOwner) {
//            Log.wtf("noteRep", it.joinToString())//todo вырезать и сделать не через жопу
//                mViewModel.testHYU(it)
//        }

        val expensesPie = binding.expensesLayout.pieChart
        val incomesPie = binding.incomesLayout.pieChart
        setPieOptions(expensesPie)
        setPieOptions(incomesPie)

        mViewModel.setIncomesBalance.observe(viewLifecycleOwner) {
            binding.topSectionLayout.tvIncomesValue.text = it.toString()
        }

        mViewModel.setExpensesBalance.observe(viewLifecycleOwner) {
            binding.topSectionLayout.tvExpensesValue.text = it.toString()
        }

        mViewModel.setTotalBalance.observe(viewLifecycleOwner) {
            binding.topSectionLayout.tvTotalBalanceValue.text = it.toString()
        }

        mViewModel.expensesPieData.observe(viewLifecycleOwner) {
            expensesPie.data = it
            expensesPie.notifyDataSetChanged()
            expensesPie.invalidate()

        }

        mViewModel.incomesPieData.observe(viewLifecycleOwner) {
            incomesPie.data = it
            incomesPie.notifyDataSetChanged()
            incomesPie.invalidate()
        }

        mViewModel.expensesTopCategoriesText.observe(viewLifecycleOwner) {
            binding.expensesLayout.tvTopCategories.text = it
        }

        mViewModel.incomesTopCategoriesText.observe(viewLifecycleOwner) {
            binding.incomesLayout.tvTopCategories.text = it
        }

        return binding.root
    }

    private fun setPieOptions(pieChart: PieChart) {
        pieChart.description.isEnabled = false
        pieChart.animateY(400)
        pieChart.legend.isEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieChart.setHoleColor(Color.TRANSPARENT)
        pieChart.setTransparentCircleAlpha(50)
        pieChart.invalidate()
    }

}


//        val pieChart = fragmentBinding.expensesLayout.pieChart
//
//
//        val entiers = ArrayList<PieEntry>()
//        entiers.add(PieEntry(80f, "Physics"))
//        entiers.add(PieEntry(80f, "Mathfds"))
//        entiers.add(PieEntry(80f, "Physics"))
//
//        val colors = ArrayList<Int>()
//        colors.add(Color.RED)
//        colors.add(resources.getColor(R.color.purple_700))
//        val pieDataSet = PieDataSet(entiers, "Pizdets")
//        pieDataSet.colors = colors
//
//        val pieData = PieData(pieDataSet)
//        pieChart.data = pieData
//
//        pieChart.description.isEnabled = false
//        pieChart.animateY(400)
//        pieChart.legend.isEnabled = false
//        pieChart.setDrawEntryLabels(false)
//        pieDataSet.setDrawValues(false)
//        pieChart.setHoleColor(Color.TRANSPARENT)
//        pieChart.setTransparentCircleAlpha(50)
//
//        pieChart.invalidate()
//
//
//        val resourceId = resources.getIdentifier("IMAGE NAME", "drawable", context?.packageName)
//        val bitmap = BitmapFactory.decodeResource(resources, resourceId)
//
//        Palette.Builder(bitmap).generate { it?.let { palette ->
//            val dominantColor = palette.getDominantColor(ContextCompat.getColor(requireContext(), R.color.white))
//        } }