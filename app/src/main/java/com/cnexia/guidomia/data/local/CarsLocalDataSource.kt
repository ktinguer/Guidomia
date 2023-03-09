package com.cnexia.guidomia.data.local

import com.cnexia.guidomia.data.model.Car
import com.cnexia.guidomia.domain.model.CarUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CarsLocalDataSource @Inject constructor(
    private val carDao: CarDao
) {

    suspend fun getCarList(): List<CarUi> = withContext(Dispatchers.IO) {
        return@withContext carDao.getAll().map(CarEntity::toCarUi)
    }

    suspend fun saveCars(cars: List<Car>) = withContext(Dispatchers.IO) {
        carDao.insertAll(cars.map {
            CarEntity(
                make = it.make,
                model = it.model,
                rating = it.rating,
                customerPrice = it.customerPrice,
                marketPrice = it.marketPrice,
                prosList = it.prosList,
                consList = it.consList
            )
        })
    }
}