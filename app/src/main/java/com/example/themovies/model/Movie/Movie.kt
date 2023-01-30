package com.example.themovies.model.Movie

data class Movie(

    val id: Int,
    val image: Image,
    val name: String,
    val poster:String?,
    val releaseDate: String?,
    val overview: String?,


    )