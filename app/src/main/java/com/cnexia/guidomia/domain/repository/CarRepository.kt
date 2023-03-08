package com.cnexia.guidomia.domain.repository

import com.cnexia.guidomia.domain.model.Cars
import com.cnexia.guidomia.util.Resource

interface CarRepository {
    suspend fun getAllCars(): Resource<Cars>
}