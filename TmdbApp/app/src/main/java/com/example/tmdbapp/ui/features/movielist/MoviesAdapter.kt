package com.example.tmdbapp.ui.features.movielist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbapp.data.networking.models.MovieDomainModel
import com.example.tmdbapp.databinding.MovieItemBinding


class MoviesAdapter() :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    val list = ArrayList<MovieDomainModel>()
    init {
        addItems(ArrayList<MovieDomainModel>())
    }

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
        return list.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movieItem = list.get(position)
        holder.bind(movieItem)

        holder.itemView.setOnClickListener {
            val extras = FragmentNavigatorExtras(holder.itemView to movieItem.title!!)
            it.findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movieItem.id,movieItem,false),extras)
        }
    }

    class MoviesViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MovieDomainModel) {
            binding.item = model
            val posterUrl = model.poster_path
            Glide.with(binding.movieIamge.context).load(posterUrl).into(binding.movieIamge)
            binding.root.transitionName = model.title
        }

    }
    fun addItems(movieItems:List<MovieDomainModel>){
        list.addAll(ArrayList<MovieDomainModel>(movieItems))
    }
    fun updateItems(movieItems: List<MovieDomainModel>){
        addItems(movieItems)
        notifyItemInserted(list.size-1)
    }

}