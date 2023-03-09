package com.cnexia.guidomia.util

import androidx.room.TypeConverter

class TypeConverter {

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return list.joinToString("/")
    }

    @TypeConverter
    fun fromStringToList(string: String): List<String> {
        return string.split("/")
    }
}