package com.example.themovies.model

import com.example.themovies.App
import com.example.themovies.R

class NetworkException(
    msg: String = App.getResString(R.string.error_internet_connection)
) : Throwable(msg)