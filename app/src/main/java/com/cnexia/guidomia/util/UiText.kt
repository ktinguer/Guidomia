package com.cnexia.guidomia.util

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
    data class DynamicString(val value: String): UiText()
    data class StringResource(@StringRes val id: Int): UiText()
}

fun UiText.asString(context: Context): String {
    return when(this){
        is UiText.StringResource -> context.getString(this.id)
        is UiText.DynamicString -> this.value
    }
}
