package com.example.mydatabindingtest.data

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.BindingAdapter
import com.example.mydatabindingtest.R

class BindingAdapters
{
    companion object {
        @JvmStatic
        @BindingAdapter("app:hideIfZero")
        fun hideIfZero(view: View, number: Int) {
            view.visibility = if (number == 0) View.GONE else View.VISIBLE
        }

        @JvmStatic
        @BindingAdapter(value = ["app:progressScaled", "android:max"], requireAll = true)
        fun setProgress(progressBar: ProgressBar, likes: Int, max: Int) {
            progressBar.progress = (likes * max / 5).coerceAtMost(max)
        }

        @JvmStatic
        @BindingAdapter("android:src")
        fun setSrc(imageView: ImageView, likes: Int)
        {
            if(likes < 5) imageView.setImageResource(R.drawable.ic_person_black_96dp)
            else if(likes < 10) imageView.setImageResource(R.drawable.ic_whatshot_black_96dp)
        }

        @JvmStatic
        @BindingAdapter("app:tint")
        fun setTint(imageView: ImageView, likes: Int)
        {
            if(likes in 5..9) imageView.setColorFilter(getColor(imageView.context, R.color.popular))
            else if(likes > 9) imageView.setColorFilter(getColor(imageView.context, R.color.star))

        }
    }
}