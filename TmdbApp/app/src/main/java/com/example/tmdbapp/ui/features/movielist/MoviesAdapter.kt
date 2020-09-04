package com.example.tmdbapp.ui.features.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbapp.BuildConfig
import com.example.tmdbapp.R
import com.example.tmdbapp.data.networking.models.response.MovieResults
import com.example.tmdbapp.databinding.MovieItemBinding

class MoviesAdapter(private var list: List<MovieResults.Result?>?) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        return MoviesViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movieItem = list?.get(position)
        if (movieItem != null) {
            holder.bind(movieItem)
        }

        holder.itemView.setOnClickListener {
            val extras = FragmentNavigatorExtras(holder.itemView to "shared_element_container")
            it.findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movieItem!!))

        }
    }

    class MoviesViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MovieResults.Result) {
            binding.item = model
            val posterUrl = "${BuildConfig.IMAGE_URL}${model.getPosterPath()}"
            Glide.with(binding.movieIamge.context).load(posterUrl).into(binding.movieIamge)
        }

    }

}