package com.parsdroid.tmdbmovieapp.data.db.entity

data class Movie(
    val backdropPath: String? = null,
    val genres: List<Int>? = null,
    val id: Int? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)