package com.example.coin.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.example.coin.R
import com.example.coin.repository.sharedprefs.spSaveMonth
import com.example.coin.repository.sharedprefs.spSaveYear
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.LocalDate
import java.util.Date

class ChoosePeriodDialogFragment : DialogFragment() {

    private val customLayout by lazy(LazyThreadSafetyMode.NONE) { requireActivity().layoutInflater.inflate(R.layout.dialog_fragment_choose_period, null) }
    private val monthPicker by lazy(LazyThreadSafetyMode.NONE) { customLayout.findViewById<NumberPicker>(R.id.month_picker) }
    private val yearPicker by lazy(LazyThreadSafetyMode.NONE) { customLayout.findViewById<NumberPicker>(R.id.year_picker)}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireActivity())

        setViewsPresets(customLayout)

        builder.setView(customLayout)
            .setTitle(requireContext().getString(R.string.choose_date_period))
            .setPositiveButton("OK") { dialog, _ ->
                spSaveMonth(monthPicker.value, requireContext())
                spSaveYear(yearPicker.value, requireContext())
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }

    private fun setViewsPresets(customLayout: View) {
        val months = arrayOf("-",
            getString(R.string.jan), getString(R.string.feb),
            getString(R.string.mar), getString(R.string.apr),
            getString(R.string.may), getString(R.string.jun),
            getString(R.string.jul), getString(R.string.aug),
            getString(R.string.sep), getString(R.string.oct),
            getString(R.string.nov), getString(R.string.dec),
        )
        monthPicker.displayedValues = months
        monthPicker.maxValue = months.size - 1
        monthPicker.minValue = 0

        yearPicker.maxValue = 2040
        yearPicker.minValue = 2000
        yearPicker.value = LocalDate.now().year
    }

}