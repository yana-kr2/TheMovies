package com.example.themovies.utils.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow


// use some useful extension from my previous project
suspend inline fun <T> Flow<T>.collectIn(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline action: suspend CoroutineScope.(T) -> Unit
) = lifecycleOwner.repeatOnLifecycle(minActiveState) {
    collect {
        action(it)
    }
}