package com.example.themovies.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.themovies.MainActivity

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }
    protected abstract fun setupView()

    private fun getMainActivity(): MainActivity? {
        activity.let { act ->
            return if (act is MainActivity) {
                act
            } else {
                null
            }
        }
    }
}