package com.cnexia.guidomia.di

import android.app.Application
import androidx.room.Room
import com.cnexia.guidomia.data.local.CarDatabase
import com.cnexia.guidomia.data.local.CarsLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCarDatabase(app: Application): CarDatabase {
        return Room.databaseBuilder(
            app,
            CarDatabase::class.java,
            "car_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCarsLocalDataSource(db: CarDatabase): CarsLocalDataSource {
        return CarsLocalDataSource(db.carDao)
    }


}