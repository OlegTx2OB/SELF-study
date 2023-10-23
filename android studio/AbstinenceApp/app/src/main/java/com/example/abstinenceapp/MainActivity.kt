package com.example.abstinenceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

//todo сделать так, чтобы при запуске таймер не работал
//todo при нажатии на настройки передать какая из кнопок выбора была прожата
//todo мб из-за одинаковых названий (например, back, может отъёбываться приложуха)
open class MainActivity : AppCompatActivity()
{

    lateinit var mCigaretteBtn: ImageButton
    lateinit var mBottleBtn: ImageButton
    lateinit var mXXXBtn: ImageButton

    lateinit var mMenuBtn: ImageButton
    lateinit var mRestartBtn: ImageButton
    lateinit var mSettingsBtn: ImageButton
    lateinit var mAchieveBtn: ImageButton

    lateinit var mTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewsInitialization()

        var timePicker: Long = 0

        mRestartBtn.setOnClickListener{
            if (timePicker + 2000 > System.currentTimeMillis()) mTextView.text = "Meow"//todo
            else Toast.makeText(
                    baseContext, "Press once again to restart!",
                    Toast.LENGTH_SHORT
                ).show()
            timePicker = System.currentTimeMillis()
        }

        mSettingsBtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun viewsInitialization()
    {
        mCigaretteBtn = findViewById(R.id.cigarette)
        mBottleBtn = findViewById(R.id.bottle)
        mXXXBtn = findViewById(R.id.xxx)

        mMenuBtn = findViewById(R.id.archieve)
        mRestartBtn = findViewById(R.id.restart)
        mSettingsBtn = findViewById(R.id.settings)
        mAchieveBtn = findViewById(R.id.achieve)

        mTextView = findViewById(R.id.textView)
    }





//    @SuppressLint("UseCompatTextViewDrawableApis")
//    fun onClickNavigationBarButtons(view: View)
//    {
//        val pressedButton = view as AppCompatButton
//
//        val disabledButton: AppCompatButton = if(!mCigaretteBtn.isEnabled) mCigaretteBtn
//        else if(!mBottleBtn.isEnabled) mBottleBtn
//        else mXXXBtn
//
//        pressedButton.compoundDrawableTintList = ColorStateList.valueOf(
//            getColor(R.color.burnt_orange))
//        pressedButton.isEnabled = false
//
//        disabledButton.compoundDrawableTintList = ColorStateList.valueOf(
//            getColor(R.color.dark_chocolate))
//        disabledButton.isEnabled = true
//    }


//    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//    Editor edit = sharedPreferences.edit();
//    edit.putBoolean("sly", false);
//    edit.commit();

}