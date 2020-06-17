package com.parsdroid.tmdbmovieapp.data.movieList

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @Json(name = "page")
    val page: Long,

    @Json(name = "total_results")
    val totalResults: Int,

    @Json(name = "total_pages")
    val totalPages: Long,

    @Json(name = "results")
    val results: List<MovieResponse>
)

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "popularity")
    val popularity: Double? = null,

    @Json(name = "vote_count")
    val voteCount: Int? = null,

    @Json(name = "video")
    val video: Boolean? = null,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "adult")
    val adult: Boolean? = null,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "original_language")
    val originalLanguage: String? = null,

    @Json(name = "original_title")
    val originalTitle: String? = null,

    @Json(name = "genre_ids")
    val genreIds: List<Int>? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "vote_average")
    val voteAverage: Double? = null,

    @Json(name = "overview")
    val overview: String? = null,

    @Json(name = "release_date")
    val releaseDate: String? = null
)