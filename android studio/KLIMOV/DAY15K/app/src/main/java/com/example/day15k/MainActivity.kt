package com.example.day15k
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val meterRadioButton: RadioButton = findViewById(R.id.radio_button_meter)
        val inputEditText: EditText = findViewById(R.id.editText)

        val button: Button = findViewById(R.id.button_converter)

        button.setOnClickListener {
            if (inputEditText.text.isEmpty())
            {
                Toast.makeText(applicationContext, "Введите длину кота", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val inputValue = inputEditText.text.toString().toFloat()
                if (meterRadioButton.isChecked)
                    inputEditText.setText(convertParrotToMeter(inputValue).toString())
                else
                    inputEditText.setText(convertMeterToParrot(inputValue).toString())
            }
        }
    }

    private fun convertParrotToMeter(parrot: Float): Float = (parrot / 7.6).toFloat()

    private fun convertMeterToParrot(meter: Float): Float = (meter * 7.6).toFloat()
}