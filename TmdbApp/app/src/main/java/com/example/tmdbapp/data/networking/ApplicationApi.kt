package com.example.tmdbapp.data.networking

import com.example.tmdbapp.data.networking.models.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApplicationApi {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : PopularMovieResponse


    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ) : MovieDetail

    @GET("/3/movie/{movieId}/recommendations")
    suspend fun getRecommendedMovies(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : RecommendedMovieResponse

}