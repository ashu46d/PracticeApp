package com.example.tmdbapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbapp.data.GetPopularMoviesUseCase
import com.example.tmdbapp.data.networking.ApplicationApi
import com.example.tmdbapp.data.repository.Repository
import com.example.tmdbapp.data.repository.RepositoryImpl
import com.example.tmdbapp.ui.features.moviedetails.GetMovieDetailUseCase
import com.example.tmdbapp.ui.features.moviedetails.MovieDetailViewModel
import com.example.tmdbapp.ui.features.movielist.MovieViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
class MyViewModelFactory : ViewModelProvider.Factory {

    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(
                GetPopularMoviesUseCase(RepositoryImpl(retrofit.create(ApplicationApi::class.java)))
            ) as T
        }
        if(modelClass.isAssignableFrom(MovieDetailViewModel::class.java)){
            return MovieDetailViewModel(
                GetMovieDetailUseCase(RepositoryImpl(retrofit.create(ApplicationApi::class.java)))
            ) as T
        }
        throw(IllegalAccessException("Unknown viewmodel class"))
    }
}