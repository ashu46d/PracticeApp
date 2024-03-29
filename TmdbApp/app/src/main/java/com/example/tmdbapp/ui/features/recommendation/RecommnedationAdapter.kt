package com.example.tmdbapp.ui.features.recommendation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import com.example.tmdbapp.databinding.RecommendedMovieItemsBinding
import com.example.tmdbapp.ui.features.moviedetails.MovieDetailFragmentDirections
import com.example.tmdbapp.ui.features.movielist.MovieFragmentDirections

class RecommnedationAdapter() :
    RecyclerView.Adapter<RecommnedationAdapter.RecommendationViewHolder>() {

    var list = ArrayList<MovieDomainModel>()

    init {
        update(ArrayList<MovieDomainModel>())
    }

    fun update(movieItems:List<MovieDomainModel>){
        list = ArrayList<MovieDomainModel>(movieItems)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendationViewHolder {
        return RecommendationViewHolder(
            RecommendedMovieItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        val movieItem = list[position]
        holder.bind(movieItem)
        holder.itemView.setOnClickListener {
            val extras = FragmentNavigatorExtras(holder.itemView to movieItem.id.toString())
            it.findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailFragmentSelf(movieItem.id,movieItem,true),extras)
        }
    }

    class RecommendationViewHolder(val binding: RecommendedMovieItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MovieDomainModel) {
            binding.movie = model
            val posterUrl = model.poster_path
            Glide.with(binding.recPosterImg.context).load(posterUrl).into(
                binding.recPosterImg
            )
            binding.root.transitionName = model.id.toString()
        }

    }

}