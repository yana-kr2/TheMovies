package com.example.themovies.model.cast


import com.google.gson.annotations.SerializedName

data class Person(
    val birthday: String,
    val deathday: Any,
    val gender: String,
    val id: Int,
    val image: Image,
    val name: String,
)