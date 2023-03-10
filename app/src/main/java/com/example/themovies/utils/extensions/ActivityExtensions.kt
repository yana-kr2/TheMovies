package com.example.themovies.utils.extensions

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.themovies.App
import com.example.themovies.R
import com.google.android.material.snackbar.Snackbar

// use some useful extension from my previous project

fun FragmentActivity.showSnackBar(message: String) {
    val rootView = window.decorView.findViewById<View>(android.R.id.content)
    val snackBar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)

    snackBar.setAction(getString(R.string.cancel)) {
        snackBar.dismiss()
    }
        .setBackgroundTint(App.getResColor(R.color.white))
        .setActionTextColor(App.getResColor(R.color.pale_black))
        .show()
}