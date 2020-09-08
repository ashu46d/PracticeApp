package com.example.tmdbapp.ui.features.cast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbapp.data.networking.models.ActorDomainModel
import com.example.tmdbapp.databinding.CastItemBinding

class CastAdapter(private var list: List<ActorDomainModel>) :
    RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CastViewHolder {
        return CastViewHolder(
            CastItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val movieItem = list[position]
        holder.bind(movieItem)

    }

    class CastViewHolder(val binding: CastItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ActorDomainModel) {
            binding.cast = model
            val posterUrl = model.profileImage
            Glide.with(binding.castImage.context).load(posterUrl).into(
                binding.castImage
            )
        }

    }

}