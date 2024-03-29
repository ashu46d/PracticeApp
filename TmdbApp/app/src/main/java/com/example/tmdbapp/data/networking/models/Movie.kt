package com.example.tmdbapp.data.networking.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Movie(
    val id: Int,
    val poster_path: String?,
    val adult: Boolean,
    val overview: String?,
    val release_date: String?,
    val original_title: String?,
    val original_language: String?,
    val title: String?,
    val genre_ids: MutableList<Int>,
    val backdrop_path: String?,
    val popularity: Double,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Double
) : Parcelable

fun Movie.toDomainModel(): MovieDomainModel {

    var imagePosterPath : String? = null
    if(this.poster_path != null) {
        imagePosterPath = "https://image.tmdb.org/t/p/w185$poster_path"

    }


    var imageBackdropPath : String? = null
    if(this.backdrop_path != null) {
        imageBackdropPath = "https://image.tmdb.org/t/p/w500$backdrop_path"

    }

    return MovieDomainModel(
        id = this.id,
        poster_path = imagePosterPath,
        backdrop_path = imageBackdropPath,
        title = this.title,
        overview = this.overview,
        release_date = this.release_date,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}

@Parcelize
data class MovieDomainModel(
    val id: Int,
    val poster_path: String?,
    val backdrop_path: String?,
    val title: String?,
    val overview: String?,
    val release_date: String?,
    val vote_average: Double,
    val vote_count: Int
): Parcelable