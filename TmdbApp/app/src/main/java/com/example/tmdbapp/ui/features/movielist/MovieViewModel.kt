package com.example.tmdbapp.ui.features.movielist


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.data.GetPopularMoviesUseCase
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import kotlinx.coroutines.launch

class MovieViewModel(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) : ViewModel() {


    private val _popularMovies = MutableLiveData<MutableList<MovieDomainModel>>(mutableListOf())
    private val mAdapter = MoviesAdapter()


    fun getAdapter():MoviesAdapter{
        return mAdapter
    }
    fun updateList(list:List<MovieDomainModel>){
        mAdapter.updateItems(list)
    }

    fun getMovies(): LiveData<MutableList<MovieDomainModel>> = _popularMovies

    fun getPopularMovies(page:Int) {
//        _popularMovies.value = mutableListOf()
        viewModelScope.launch {
            _popularMovies.value = getPopularMoviesUseCase.execute(page)
        }


    }
}