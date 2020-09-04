package com.example.tmdbapp.data.repository

import com.example.tmdbapp.BuildConfig
import com.example.tmdbapp.data.networking.ApplicationApi
import com.example.tmdbapp.data.networking.models.response.MovieResults
import retrofit2.Call

const val PAGE = 1
//const val API_KEY = "7954c2f5861a237b14ffdd106e547eb7"
const val LANGUAGE = "en-US"
const val CATEGORY = "popular"

class RepositoryImpl(private val api: ApplicationApi):Repository {

    override suspend fun getMovieList(): Call<MovieResults> {

        return api.listOfMovies(
            CATEGORY,
            BuildConfig.API_KEY,
            LANGUAGE,
            PAGE
        )
    }
}