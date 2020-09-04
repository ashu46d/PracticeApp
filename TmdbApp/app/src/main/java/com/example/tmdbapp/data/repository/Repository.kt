package com.example.tmdbapp.data.repository

import com.example.tmdbapp.data.networking.models.response.MovieResults
import com.example.tmdbapp.data.networking.ApplicationApi
import retrofit2.Call


interface Repository {


    suspend fun getMovieList(): Call<MovieResults>
}