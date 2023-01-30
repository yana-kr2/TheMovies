package com.example.themovies.utils.extensions

import com.example.themovies.model.NetworkException

fun Throwable?.isConnectionError(): Boolean {
    return this is NetworkException
}