package com.example.tmdbapp.ui.features.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.data.GetPopularMoviesUseCase
import com.example.tmdbapp.data.networking.Result
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import kotlinx.coroutines.launch

class MovieViewModel(private val getPopularMoviesUseCase: GetPopularMoviesUseCase): ViewModel() {


    private lateinit var popularMoviesLiveData: MutableLiveData<List<MovieDomainModel>>


    fun getMovies(): LiveData<List<MovieDomainModel>> {
        if(!::popularMoviesLiveData.isInitialized) {
            popularMoviesLiveData = MutableLiveData()
            popularMoviesLiveData.value = null
        }

        return popularMoviesLiveData
    }

    fun getPopularMovies() {
        viewModelScope.launch {

            getPopularMoviesUseCase.execute().also {
                    result ->
                when(result) {
                    is Result.Success -> {
                        val movies: List<MovieDomainModel>?  = result.data as List<MovieDomainModel>?
//                        if(movies.isNullOrEmpty()) {
//                            isLoadingLiveData.value = false
//                            isErrorLiveData.value = true
//                        } else {
//                            isLoadingLiveData.value = false
                            popularMoviesLiveData.value = movies
//                        }
                    }

//                    is Result.Error -> {
//                        isLoadingLiveData.value = false
//                        isErrorLiveData.value = true
//                    }
                }


            }
        }
    }


}