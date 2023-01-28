package com.example.themovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorRes
import com.example.themovies.databinding.ActivityMainBinding

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