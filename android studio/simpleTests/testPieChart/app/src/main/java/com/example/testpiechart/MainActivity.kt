package com.example.testpiechart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pieChart: PieChart = findViewById(R.id.chart)

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
        pieChart.animateY(1000)
        pieChart.legend.isEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieDataSet.setDrawValues(false)
        pieChart.setHoleColor(Color.TRANSPARENT)
        pieChart.setTransparentCircleAlpha(0)

        pieChart.invalidate()

    }
}