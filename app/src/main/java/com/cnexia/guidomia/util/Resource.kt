package com.cnexia.guidomia.util

// A generic class that contains data and status about loading this data.
sealed class Resource<T>(
    val data: T? = null,
    val message: UiText? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T> : Resource<T>(null)
    class Error<T>(data: T? = null, message: UiText) : Resource<T>(data, message)
}
