package com.example.tmdbapp.ui.features.moviedetails

import com.example.tmdbapp.data.networking.Result
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import com.example.tmdbapp.data.networking.models.response.MovieDetailDomainModel
import com.example.tmdbapp.data.repository.Repository
import java.io.IOException

class GetMovieDetailUseCase(
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