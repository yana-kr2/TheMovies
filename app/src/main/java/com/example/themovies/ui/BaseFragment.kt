package com.example.themovies.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
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

    fun Fragment.getNavController(): NavController? {
        return try {
            NavHostFragment.findNavController(this)
        } catch (e: IllegalStateException) {
            null
        }
    }
}