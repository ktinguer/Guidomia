package com.cnexia.guidomia.data.repository

import com.cnexia.guidomia.R
import com.cnexia.guidomia.data.model.Car
import com.cnexia.guidomia.data.model.toCarUi
import com.cnexia.guidomia.data.remote.CarsApi
import com.cnexia.guidomia.domain.model.Cars
import com.cnexia.guidomia.domain.repository.CarRepository
import com.cnexia.guidomia.util.Resource
import com.cnexia.guidomia.util.UiText
import com.google.gson.JsonSyntaxException
import java.io.IOException

class CarRepositoryImpl(
    private val remoteDataSource: CarsApi
) : CarRepository {

    override suspend fun getAllCars(): Resource<Cars> {
        return try {
            val response = remoteDataSource.getAllCars().map(Car::toCarUi)
            Resource.Success(data = Cars(response))
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            e.message?.let { Resource.Error(message = UiText.DynamicString(it)) }
                ?: Resource.Error(message = UiText.StringResource(R.string.could_not_load_cars))
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(message = UiText.StringResource(R.string.could_not_load_cars))
        }
    }
}