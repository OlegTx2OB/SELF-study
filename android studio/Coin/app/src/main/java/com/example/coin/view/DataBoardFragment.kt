package com.example.coin.view

import android.graphics.Color
import com.example.coin.R.layout.fragment_databoard
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coin.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


class DataBoardFragment : Fragment()
{

    //private var _binding: FragmentIncomesOutcomesBinding? = null

    //private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        val rootView: View = inflater.inflate(fragment_databoard, container, false)

        val pieChart: PieChart = rootView.findViewById(R.id.pieChart)

        val entiers = ArrayList<PieEntry>()
        entiers.add(PieEntry(80f, "Physics"))
        entiers.add(PieEntry(80f, "Mathfds"))
        entiers.add(PieEntry(80f, "Physics"))

        val colors = ArrayList<Int>()
        colors.add(Color.RED)
        colors.add(Color.YELLOW)
        colors.add(Color.GREEN)
        colors.add(Color.RED)
        val pieDataSet = PieDataSet(entiers, "Pizdets")
        pieDataSet.colors = colors

        val pieData = PieData(pieDataSet)
        pieChart.data = pieData

        pieChart.description.isEnabled = false
        pieChart.animateY(400)
        pieChart.legend.isEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieDataSet.setDrawValues(false)
        pieChart.setHoleColor(Color.TRANSPARENT)
        pieChart.setTransparentCircleAlpha(50)

        pieChart.invalidate()

        return rootView
    }
}