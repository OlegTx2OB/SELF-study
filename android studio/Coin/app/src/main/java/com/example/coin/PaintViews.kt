package com.example.coin

import android.content.Context
import android.util.TypedValue
import androidx.cardview.widget.CardView


fun getUnpressedCardColor(context: Context): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(com.google.android.material.R.attr.colorSurfaceContainerHighest, typedValue, true)
    return typedValue.data
}

fun getPressedCardColor(context: Context): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(com.google.android.material.R.attr.colorPrimaryContainer, typedValue, true)
    return typedValue.data
}
fun paintPressedCardView(cardView: CardView, context: Context) {
    cardView.setCardBackgroundColor(getPressedCardColor(context))
}

fun paintUnpressedCardViews(viewList: List<CardView>, context: Context) {
    for (cardView in viewList) {
        cardView.setCardBackgroundColor(getUnpressedCardColor(context))
    }
}