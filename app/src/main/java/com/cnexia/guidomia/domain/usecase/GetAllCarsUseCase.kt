package com.cnexia.guidomia.domain.usecase

import com.cnexia.guidomia.domain.model.Cars
import com.cnexia.guidomia.domain.repository.CarRepository
import com.cnexia.guidomia.util.Resource

class GetAllCarsUseCase(
    private val repository: CarRepository
) {
    suspend operator fun invoke(): Resource<Cars> {
        return repository.getAllCars()
    }
}