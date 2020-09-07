package com.example.tmdbapp.data.networking.models.response

import com.example.tmdbapp.data.networking.models.Movie

data class RecommendedMovieResponse (
    val page: Int,
    val results: List<Movie>?)