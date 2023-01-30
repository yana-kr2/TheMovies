package com.example.themovies.model

import com.example.themovies.utils.commonError
import com.example.themovies.utils.extensions.isConnectionError

data class Resource<out T>(val isSuccessful: Boolean, val data: T?, val error: Throwable? = null) {

    val anyError: Throwable
        get() = error ?: RuntimeException(commonError())

    val errorMsg: String
        get() = anyError.localizedMessage ?: commonError()

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                true,
                data
            )
        }

        fun <T> error(msg: String?): Resource<T> {
            return error(RuntimeException(msg ?: commonError()))
        }

        fun <T> error(error: Throwable? = null): Resource<T> {
            val e = if (error.isConnectionError()){
                NetworkException()
            } else {
                error ?: RuntimeException(commonError())
            }
            return Resource(
                false,
                null,
                e
            )
        }

        fun <T> cancel(): Resource<T> {
            return Resource(
                false,
                null,
                null
            )
        }
    }
}