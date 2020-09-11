package com.example.tmdbapp.data

import android.util.Log
import com.example.tmdbapp.data.repository.Repository
import java.io.IOException
import com.example.tmdbapp.data.networking.Result
import com.example.tmdbapp.data.networking.models.MovieDomainModel


class GetPopularMoviesUseCase (
    private val movieRepository: Repository) {

//    private var page: Int =0

    suspend fun execute(page:Int): MutableList<MovieDomainModel> {
//        page++
        Log.d("TAG1111", "execute: ${page}")
        val movies = mutableListOf<MovieDomainModel>()
        movieRepository.getPopularMovies(page)?.let{
            it.filter {
                it.poster_path !=null
            }
            movies.addAll(it)
        }
        return movies

    }
}