package com.cnexia.guidomia.di

import com.cnexia.guidomia.data.local.CarsLocalDataSource
import com.cnexia.guidomia.data.remote.CarsApi
import com.cnexia.guidomia.data.repository.CarRepositoryImpl
import com.cnexia.guidomia.domain.repository.CarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCarRepository(remoteDataSource: CarsApi, localDataSource: CarsLocalDataSource): CarRepository {
        return CarRepositoryImpl(remoteDataSource, localDataSource)
    }
}