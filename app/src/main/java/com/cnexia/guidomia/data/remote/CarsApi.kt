package com.cnexia.guidomia.data.remote

import android.content.Context
import com.cnexia.guidomia.data.model.Car
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CarsApi @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {


    suspend fun getAllCars(): List<Car> {
        delay(3000)
        val json = loadJSONFromAsset("car_list.json")
        return gson.fromJson(json, object : TypeToken<List<Car>>() {}.type)
    }

    private suspend fun loadJSONFromAsset(fileName: String): String = withContext(Dispatchers.IO) {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return@withContext String(buffer, Charsets.UTF_8)
    }

}