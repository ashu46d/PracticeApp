package com.example.tmdbapp.data.networking.models.response

import com.example.tmdbapp.data.networking.models.Actor


data class MovieCreditResponse(
    val id: Int,
    val cast: List<Actor>)
