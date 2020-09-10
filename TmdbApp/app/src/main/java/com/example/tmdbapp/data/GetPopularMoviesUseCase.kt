package com.example.tmdbapp.data

import com.example.tmdbapp.data.repository.Repository
import java.io.IOException
import com.example.tmdbapp.data.networking.Result
import com.example.tmdbapp.data.networking.models.MovieDomainModel


class GetPopularMoviesUseCase (
    private val movieRepository: Repository) {

    private var page: Int =1

    suspend fun execute(): MutableList<MovieDomainModel> {
        page++
        val movies = mutableListOf<MovieDomainModel>()
        movieRepository.getPopularMovies(page)?.let{
            it.filter {
                it.poster_path !=null
            }
            movies.addAll(it)
        }
        return movies

//        return try {
//
//            movieRepository.getPopularMovies(page)?.let {
//                Result.Success(it.filter { it.poster_path != null })
//            } ?: Result.Error("No Data")
//        } catch (e: IOException) {
//            Result.Error("getPopularMovies error")
//        }
    }
}