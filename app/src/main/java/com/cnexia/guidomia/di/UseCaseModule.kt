package com.cnexia.guidomia.di

import com.cnexia.guidomia.domain.repository.CarRepository
import com.cnexia.guidomia.domain.usecase.GetAllCarsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideUseCase(carsRepository: CarRepository): GetAllCarsUseCase{
        return GetAllCarsUseCase(carsRepository)
    }
}