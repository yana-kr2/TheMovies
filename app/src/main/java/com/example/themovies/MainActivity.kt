package com.example.themovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.themovies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}