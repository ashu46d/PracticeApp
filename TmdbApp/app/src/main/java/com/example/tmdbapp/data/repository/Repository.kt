package com.example.tmdbapp.data.repository

import com.example.tmdbapp.data.networking.models.response.MovieResults
import com.example.tmdbapp.data.networking.ApplicationApi
import com.example.tmdbapp.data.networking.models.ActorDomainModel
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import com.example.tmdbapp.data.networking.models.response.MovieDetailDomainModel
import retrofit2.Call


interface Repository {


    suspend fun getRecommendedMovies(movieId: Int): List<MovieDomainModel>?

    suspend fun getMovieCredits(movieId: Int): List<ActorDomainModel>?

    suspend fun getMovieDetail(movieId: Int): MovieDetailDomainModel

    suspend fun getPopularMovies(page: Int): List<MovieDomainModel>?
}


