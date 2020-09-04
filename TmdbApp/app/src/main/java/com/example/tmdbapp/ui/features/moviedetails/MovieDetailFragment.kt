package com.example.tmdbapp.ui.features.moviedetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tmdbapp.BuildConfig
import com.example.tmdbapp.R
import com.example.tmdbapp.databinding.FragmentMovieDetailBinding
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {
    val args: MovieDetailFragmentArgs by navArgs()
    lateinit var binding:FragmentMovieDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_detail,container,false)
        Log.d("TAG111", "onCreateView: ${args.idMyArg.getTitle()}")

        return inflater.inflate(R.layout.fragment_movie_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load("${BuildConfig.IMAGE_URL}${args.idMyArg.getBackdropPath()}").into(expandedImage)

    }


}