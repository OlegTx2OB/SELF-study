package com.example.coin

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.cardview.widget.CardView


/*
    com.google.android.material.R.attr.colorSurfaceContainerHighest unpressed cardview
    com.google.android.material.R.attr.colorPrimaryContainer pressed cardview
*/

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