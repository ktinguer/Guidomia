package com.cnexia.guidomia.data.repository

import com.cnexia.guidomia.R
import com.cnexia.guidomia.data.local.CarsLocalDataSource
import com.cnexia.guidomia.data.remote.CarsApi
import com.cnexia.guidomia.domain.model.Cars
import com.cnexia.guidomia.domain.repository.CarRepository
import com.cnexia.guidomia.util.Resource
import com.cnexia.guidomia.util.UiText
import com.google.gson.JsonSyntaxException
import java.io.IOException

class CarRepositoryImpl(
    private val remoteDataSource: CarsApi,
    private val localDataSource: CarsLocalDataSource
) : CarRepository {

    override suspend fun getAllCars(): Resource<Cars> {
        return try {
            val localCars = localDataSource.getCarList()
            if (localCars.isNotEmpty()) return Resource.Success(data = Cars(localCars))

            val remoteCars = remoteDataSource.getAllCars()
            localDataSource.saveCars(remoteCars)

            return Resource.Success(data = Cars(localDataSource.getCarList()))

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