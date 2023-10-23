package com.example.datetimepickerscheck
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var dateButton: Button
    private lateinit var timeButton: Button

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dateButton = findViewById(R.id.dateButton)
        timeButton = findViewById(R.id.timeButton)

        dateButton.setOnClickListener {
            showTimePicker()

            showDatePicker()
        }

        timeButton.setOnClickListener {
            showTimePicker()
        }
    }

    private fun showDatePicker() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year1, month2, dayOfMonth3 ->
            val dateTextView = findViewById<TextView>(R.id.dateTextView)
            dateTextView.text = "$dayOfMonth3/${month2 + 1}/$year1"
        }, year, month, day)//todo цвет стиля последним аргументом можно выбрать

        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            // Обработка выбранного времени
            val selectedTime = Calendar.getInstance()
            selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            selectedTime.set(Calendar.MINUTE, minute)
            // Дальнейшая обработка выбранного времени
        }, hour, minute, true)

        timePickerDialog.show()
    }
}