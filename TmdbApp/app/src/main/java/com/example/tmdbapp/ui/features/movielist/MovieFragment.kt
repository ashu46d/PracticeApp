package com.example.tmdbapp.ui.features.movielist


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbapp.*
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import com.example.tmdbapp.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {


    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(
            MovieViewModel::class.java
        )
    }

    private lateinit var binding: FragmentMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.getPopularMovies()
        mViewModel.getMovies().observe(viewLifecycleOwner, Observer {
            it?.let{
                setAdapter(it)
            }
        })
    }

    fun setAdapter(listOfMovies: List<MovieDomainModel>) {
        Log.d("TAG111", "setAdapter: ${listOfMovies}")
        val adapter = MoviesAdapter(listOfMovies)
        binding.movieRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.movieRecyclerView.adapter = adapter
    }


}