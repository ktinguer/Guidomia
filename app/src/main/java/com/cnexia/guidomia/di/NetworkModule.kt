package com.cnexia.guidomia.di

import android.content.Context
import com.cnexia.guidomia.data.remote.CarsApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideCarsApi(@ApplicationContext context: Context, gson: Gson) = CarsApi(context, gson)

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}