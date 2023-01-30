package com.example.themovies.utils.extensions

import com.example.themovies.model.NetworkException


// use some useful extension from my previous project
fun Throwable?.isConnectionError(): Boolean {
    return this is NetworkException
}