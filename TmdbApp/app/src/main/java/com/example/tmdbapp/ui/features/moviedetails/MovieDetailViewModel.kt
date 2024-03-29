package com.example.tmdbapp.ui.features.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.data.networking.models.Actor
import com.example.tmdbapp.data.networking.models.ActorDomainModel
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import com.example.tmdbapp.data.networking.models.response.MovieDetailDomainModel
import com.example.tmdbapp.data.networking.models.response.RecommendedMovieResponse
import com.example.tmdbapp.ui.features.movielist.MoviesAdapter
import com.example.tmdbapp.ui.features.recommendation.RecommnedationAdapter
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val getMovieDetailUseCase: GetMovieDetailUseCase) : ViewModel() {
    private lateinit var movieDetail: MutableLiveData<MovieDetailDomainModel>
    private lateinit var recommendedMovies:MutableLiveData<List<MovieDomainModel>>
    private lateinit var castList:MutableLiveData<List<ActorDomainModel>>

    private val mAdapter = RecommnedationAdapter()

    fun getAdapter(): RecommnedationAdapter {
        return mAdapter
    }

    fun updateList(list:List<MovieDomainModel>){
        mAdapter.update(list)
    }

    fun getData(movieId: Int):LiveData<MovieDetailDomainModel>{
        if(!::movieDetail.isInitialized) {
            movieDetail = MutableLiveData()
            movieDetail.value = null
        }
        return movieDetail
    }
    fun getRecommendation(movieId: Int):LiveData<List<MovieDomainModel>>{
        if(!::recommendedMovies.isInitialized) {
            recommendedMovies = MutableLiveData()
            recommendedMovies.value = null
        }
        return recommendedMovies
    }

    fun getCast(movieId: Int):LiveData<List<ActorDomainModel>>{
        if(!::castList.isInitialized) {
            castList = MutableLiveData()
            castList.value = null
        }
        return castList
    }

    fun getMovieDetail(movieId:Int){
        viewModelScope.launch {
            movieDetail.value = getMovieDetailUseCase.getMovieDetail(movieId )
            recommendedMovies.value = getMovieDetailUseCase.getRecommendedMovies(movieId)
            castList.value = getMovieDetailUseCase.getCast(movieId)
        }
    }

}