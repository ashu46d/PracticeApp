package com.example.tmdbapp.data.repository

import com.example.tmdbapp.BuildConfig
import com.example.tmdbapp.data.networking.ApplicationApi
import com.example.tmdbapp.data.networking.models.ActorDomainModel
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import com.example.tmdbapp.data.networking.models.response.MovieDetailDomainModel
import com.example.tmdbapp.data.networking.models.response.MovieResults
import com.example.tmdbapp.data.networking.models.response.RecommendedMovieResponse
import com.example.tmdbapp.data.networking.models.response.toDomainModel
import com.example.tmdbapp.data.networking.models.toDomainModel
import retrofit2.Call

const val PAGE = 1
//const val API_KEY = "7954c2f5861a237b14ffdd106e547eb7"
const val LANGUAGE = "en-US"
const val CATEGORY = "popular"

class RepositoryImpl(private val movieApi: ApplicationApi):Repository {

    override suspend fun getCast(movieId: Int): List<ActorDomainModel>? {
        return movieApi.getCast(movieId,BuildConfig.API_KEY).cast.map {
            it.toDomainModel()
        }
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetailDomainModel {
        return movieApi.getMovieDetail(movieId,BuildConfig.API_KEY, LANGUAGE).toDomainModel()
    }

    override suspend fun getRecommendedMovies(movieId: Int): List<MovieDomainModel>? =
        movieApi.getRecommendedMovies(movieId, BuildConfig.API_KEY , "en-US", 1)
            .results
            ?.map { it.toDomainModel()}

    override suspend fun getPopularMovies(page: Int) =
        movieApi.getPopularMovies(BuildConfig.API_KEY , "en-US", page)
            .results
            ?.map {
                it.toDomainModel()
            }

}