package com.cnexia.guidomia.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cnexia.guidomia.domain.model.CarUi

@Entity(tableName = "cars")
data class CarEntity(
    val make: String,
    val model: String,
    val rating: Int,
    val customerPrice: Double,
    val marketPrice: Double,
    val prosList: List<String>,
    val consList: List<String>,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)

fun CarEntity.toCarUi(): CarUi {
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
