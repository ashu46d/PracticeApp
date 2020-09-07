package com.example.tmdbapp.data.networking.models.response

import com.example.tmdbapp.data.networking.models.Movie

data class PopularMovieResponse (
    val page: Int,
    val results: List<Movie>?)
