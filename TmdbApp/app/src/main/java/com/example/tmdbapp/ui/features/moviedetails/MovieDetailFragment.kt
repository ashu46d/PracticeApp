package com.example.tmdbapp.ui.features.moviedetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tmdbapp.BuildConfig
import com.example.tmdbapp.MyViewModelFactory
import com.example.tmdbapp.R
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import com.example.tmdbapp.databinding.FragmentMovieDetailBinding
import com.example.tmdbapp.ui.features.movielist.MovieViewModel
import com.example.tmdbapp.ui.features.movielist.MoviesAdapter
import com.example.tmdbapp.ui.features.recommendation.RecommnedationAdapter
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : Fragment() {
    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelFactory()).get(
            MovieDetailViewModel::class.java
        )
    }
    val args: MovieDetailFragmentArgs by navArgs()
    lateinit var binding: FragmentMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_detail,
            container,
            false
        ) as FragmentMovieDetailBinding

        mViewModel.getData(args.idMovieId).observe(viewLifecycleOwner, Observer {
            binding.movieDetail = it
        })

        mViewModel.getRecommendation(args.idMovieId).observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("RECOMM", "onCreateView: ${it}")
                binding.recommendationsRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.recommendationsRecyclerView.adapter = RecommnedationAdapter(it)
            }
        })
        mViewModel.getCast(args.idMovieId).observe(viewLifecycleOwner, Observer {
            it?.let{
                Log.d("ACTORS", "onCreateView: ${it}")
            }
        })

        mViewModel.getMovieDetail(args.idMovieId)

        binding.movie = args.idMovie
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load("${args.idMovie.backdrop_path}").into(binding.expandedImage)
        Glide.with(requireContext()).load("${args.idMovie.poster_path}").into(binding.posterImage)
    }


}