package com.example.tmdbapp.data.networking

import com.example.tmdbapp.data.networking.models.response.MovieResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApplicationApi {

    @GET("/3/movie/{category}")
    fun listOfMovies(
        @Path("category") category: String,
        @Query("api_key")  api_key: String,
        @Query("language") language: String,
        @Query("page")  page: Int
    ):Call<MovieResults>


}