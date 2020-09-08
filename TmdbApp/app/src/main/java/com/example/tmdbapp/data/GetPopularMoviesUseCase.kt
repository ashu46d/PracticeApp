package com.example.tmdbapp.data

import com.example.tmdbapp.data.repository.Repository
import java.io.IOException
import com.example.tmdbapp.data.networking.Result


class GetPopularMoviesUseCase (
    private val movieRepository: Repository) {

    private var page: Int =1

    suspend fun execute(): Result<Any> {
        return try {
            movieRepository.getPopularMovies(page)?.let {
                Result.Success(it.filter { it.poster_path != null })
            } ?: Result.Error("No Data")
        } catch (e: IOException) {
            Result.Error("getPopularMovies error")
        }
    }
}