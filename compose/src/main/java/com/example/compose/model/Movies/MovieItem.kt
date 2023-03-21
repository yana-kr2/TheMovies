package com.example.compose.model.Movies


import com.google.gson.annotations.SerializedName

data class MovieItem(
    val averageRuntime: Int,
    val ended: String,
    val genres: List<String>,
    val id: Int,
    val image: Image,
    val language: String,
    val name: String,
    val officialSite: String,
    val premiered: String,
    val runtime: Int,
    val status: String,
    val summary: String,
    val type: String,
    val updated: Int,
    val url: String,
    val weight: Int
)