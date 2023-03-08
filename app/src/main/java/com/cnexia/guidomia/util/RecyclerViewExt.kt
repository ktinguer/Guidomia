package com.cnexia.guidomia.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addDivider(
    context: Context,
    dividerDrawable: Drawable?
) {
    val divider = dividerDrawable ?: return
    val dividerItemDecoration = DividerItem(context, divider)
    dividerItemDecoration.setDrawable(divider)
    addItemDecoration(dividerItemDecoration)
}

