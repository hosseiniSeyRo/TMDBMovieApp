package com.parsdroid.tmdbmovieapp.ui


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ResponseState<out R> {

    data class Error(val exception: Exception) : ResponseState<Nothing>()
    data class Success<out T>(val data: T) : ResponseState<T>()
    object Loading : ResponseState<Nothing>()


    override fun toString(): String {
        return when (this) {
            is Error -> "Error[exception=$exception]"
            is Success<*> -> "Success[data=$data]"
            Loading -> "Loading"
        }
    }
}