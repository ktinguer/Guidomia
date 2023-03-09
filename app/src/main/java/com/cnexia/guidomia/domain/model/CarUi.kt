package com.cnexia.guidomia.domain.model

import androidx.annotation.DrawableRes
import com.cnexia.guidomia.R

data class CarUi(
    val make: String,
    val model: String,
    val rating: Int,
    val customerPrice: Double,
    val marketPrice: Int,
    val prosList: List<String>,
    val consList: List<String>,
    var expanded: Boolean
) {
    val imageId: Int
        @DrawableRes
        get() {
            return when (model) {
                "Range Rover" -> R.drawable.range_rover
                "Roadster" -> R.drawable.alpine_roadster
                "3300i" -> R.drawable.bmw_330i
                "GLE coupe" -> R.drawable.mercedez_benz_glc
                else -> R.drawable.default_car_image
            }
        }
}
