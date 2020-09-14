package com.example.tmdbapp.ui.features.movielist


import android.app.SharedElementCallback
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.*
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import com.example.tmdbapp.databinding.FragmentMovieBinding
import com.example.tmdbapp.ui.features.base.BaseFragment
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale

class MovieFragment : Fragment() {
    private lateinit var moviesAdapter: MoviesAdapter

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
        postponeEnterTransition()
        binding.root.doOnPreDraw { startPostponedEnterTransition() }

        exitTransition = Hold()
        moviesAdapter = mViewModel.getAdapter()

        binding.movieRecyclerView.apply {
            val manager = GridLayoutManager(context, 3)
            layoutManager = manager
            adapter = moviesAdapter
        }
        var page = 1
        mViewModel.getPopularMovies(page)
        binding.movieRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(!recyclerView.canScrollVertically(1)){
                    mViewModel.getPopularMovies(page)
                    page++
                }
            }
        })

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getMovies().observe(viewLifecycleOwner, Observer {
            mViewModel.updateList(it)
        })
    }


}