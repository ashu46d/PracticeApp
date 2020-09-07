package com.example.tmdbapp.ui.features.cast

import com.example.tmdbapp.data.networking.models.MovieDomainModel
import com.example.tmdbapp.data.networking.models.response.MovieDetailDomainModel
import com.example.tmdbapp.data.repository.Repository

class GetCastUseCase(
    private val movieRepository: Repository
) {
    suspend fun getRecommendedMovies(movieId: Int): List<MovieDomainModel> {

        movieRepository.getRecommendedMovies(movieId).let {
            return it!!
        }

    }

    suspend fun getMovieDetail(movieId: Int): MovieDetailDomainModel {
        movieRepository.getMovieDetail(movieId).let {
            return it
        }
    }
}