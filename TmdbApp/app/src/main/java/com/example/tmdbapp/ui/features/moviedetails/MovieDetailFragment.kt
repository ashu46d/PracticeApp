package com.example.tmdbapp.ui.features.moviedetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbapp.BuildConfig
import com.example.tmdbapp.MyViewModelFactory
import com.example.tmdbapp.R
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import com.example.tmdbapp.databinding.FragmentMovieDetailBinding
import com.example.tmdbapp.ui.features.cast.CastAdapter
import com.example.tmdbapp.ui.features.movielist.MovieViewModel
import com.example.tmdbapp.ui.features.movielist.MoviesAdapter
import com.example.tmdbapp.ui.features.recommendation.RecommnedationAdapter
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
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

        postponeEnterTransition()
        binding.root.doOnPreDraw { startPostponedEnterTransition() }
        exitTransition = Hold()
        sharedElementEnterTransition = MaterialContainerTransform()
        mViewModel.getData(args.idMovieId).observe(viewLifecycleOwner, Observer {
            binding.movieDetail = it
        })

        mViewModel.getRecommendation(args.idMovieId).observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.recommendationsRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                val adapter = RecommnedationAdapter(it)
                binding.recommendationsRecyclerView.adapter = adapter
                if (it.isEmpty()) {
                    binding.recomTitle.visibility = View.GONE
                }
            }
        })
        mViewModel.getCast(args.idMovieId).observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.castRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.castRecyclerView.adapter = CastAdapter(it)
            }
        })

        mViewModel.getMovieDetail(args.idMovieId)

        binding.movie = args.idMovie
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!args.idSelf)
            binding.root.transitionName = args.idMovie.title
        else
            binding.root.transitionName = args.idMovie.id.toString()
        Glide.with(requireContext())
            .load("${args.idMovie.backdrop_path}").into(binding.expandedImage)
        Glide.with(requireContext()).load("${args.idMovie.poster_path}").into(binding.posterImage)
    }


}