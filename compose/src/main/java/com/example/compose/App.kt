package com.example.compose

import android.app.Application
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun appContext(): App {
            return instance as App
        }

        fun getResString(resId: Int, vararg formatArgs: Any?): String {
            return appContext().resources.getString(resId, *formatArgs)
        }

        fun getStringArray(resId: Int): Array<String> {
            return appContext().resources.getStringArray(resId)
        }

        fun getResColor(resId: Int): Int {
            return ContextCompat.getColor(this.appContext(), resId)
        }

        fun getResDrawable(resId: Int): Drawable? {
            return ContextCompat.getDrawable(this.appContext(), resId)
        }

        fun getResFont(resId: Int): Typeface? {
            return ResourcesCompat.getFont(this.appContext(), resId)
        }
    }
}