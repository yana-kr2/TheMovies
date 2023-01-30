package com.example.themovies.utils

import android.content.Context
import android.widget.Toast
import com.example.themovies.App
import com.example.themovies.R

fun showToast(context: Context?, text: String) {
    context?.let {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}

fun commonError(): String = App.getResString(R.string.error_common)