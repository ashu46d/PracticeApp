package com.example.tmdbapp.ui.features.movielist


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.data.GetPopularMoviesUseCase
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import kotlinx.coroutines.launch

class MovieViewModel(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) : ViewModel() {


//Needs to be chaged
    private val _popularMovies = MutableLiveData<MutableList<MovieDomainModel>>(mutableListOf(MovieDomainModel(0,"","","","","",0.0,0)))


    fun getMovies(): LiveData<MutableList<MovieDomainModel>> = _popularMovies

    fun getPopularMovies() {

        viewModelScope.launch {
            _popularMovies.value = getPopularMoviesUseCase.execute()
        }


    }
}