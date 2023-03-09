package com.cnexia.guidomia.data.model

import com.cnexia.guidomia.domain.model.CarUi

data class Car(
    val make: String,
    val model: String,
    val rating: Int,
    val customerPrice: Double,
    val marketPrice: Double,
    val prosList: List<String>,
    val consList: List<String>
)

fun Car.toCarUi(): CarUi {
    return CarUi(
        make = make,
        model = model,
        rating = rating,
        customerPrice = customerPrice,
        marketPrice = marketPrice.toInt() / 1000,
        prosList = prosList,
        consList = consList,
        expanded = false
    )
}
