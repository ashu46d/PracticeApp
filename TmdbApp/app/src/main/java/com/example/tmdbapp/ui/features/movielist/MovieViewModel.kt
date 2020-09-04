package com.example.tmdbapp.ui.features.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.data.networking.models.response.MovieResults
import com.example.tmdbapp.data.repository.Repository
import com.example.tmdbapp.ui.features.base.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.Call

class MovieViewModel(private var repository: Repository): BaseViewModel() {

    private val movieResponse = MutableLiveData<Call<MovieResults>>()

    fun getMovies(): MutableLiveData<Call<MovieResults>> {
                viewModelScope.launch {
            try {
                movieResponse.value =  repository.getMovieList()
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
        return movieResponse
    }
}