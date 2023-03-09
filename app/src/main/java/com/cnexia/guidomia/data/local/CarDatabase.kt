package com.cnexia.guidomia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cnexia.guidomia.util.TypeConverter


@Database(
    entities = [CarEntity::class],
    version = 1
)
@TypeConverters(TypeConverter::class)
abstract class CarDatabase : RoomDatabase() {
    abstract val carDao: CarDao
}