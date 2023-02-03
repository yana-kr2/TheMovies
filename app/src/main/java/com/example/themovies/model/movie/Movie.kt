package com.example.themovies.model.movie


data class Movie(
    val id: Int,
    val image: Image,
    val name: String?,
    val summary: String,
    val premiered: String,
    val genres: List<String>,
    val ended: String
)




