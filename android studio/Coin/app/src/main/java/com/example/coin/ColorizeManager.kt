package com.example.coin

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.cardview.widget.CardView

val COLOR_ATTR_UNPRESSED_CARD = com.google.android.material.R.attr.colorSurfaceContainerHighest
val COLOR_ATTR_PRESSED_CARD: Int = com.google.android.material.R.attr.colorPrimaryContainer
val COLOR_ATTR_INCOMES_CARD: Int = R.attr.colorIncomes
val COLOR_ATTR_EXPENSES_CARD: Int = R.attr.colorExpenses

fun getColorAttribute(@AttrRes attributeId: Int, context: Context): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(attributeId, typedValue, true)
    return typedValue.data
}

fun paintCardViews(viewList: List<CardView>, @AttrRes attributeId: Int, context: Context) {
    for (cardView in viewList) {
        cardView.setCardBackgroundColor(
            getColorAttribute(
                attributeId,
                context
            )
        )
    }
}