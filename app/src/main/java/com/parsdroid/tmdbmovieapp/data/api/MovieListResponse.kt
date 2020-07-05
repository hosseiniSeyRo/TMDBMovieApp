package com.parsdroid.tmdbmovieapp.data.api

import com.parsdroid.tmdbmovieapp.data.db.entity.Movie
import com.parsdroid.tmdbmovieapp.data.db.entity.MovieType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @Json(name = "page")
    var page: Long,

    @Json(name = "total_results")
    var totalResults: Int,

    @Json(name = "total_pages")
    var totalPages: Long,

    @Json(name = "results")
    var results: List<MovieResponse>
)

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "popularity")
    var popularity: Double?,

    @Json(name = "vote_count")
    var voteCount: Int?,

    @Json(name = "video")
    var video: Boolean?,

    @Json(name = "poster_path")
    var posterPath: String?,

    @Json(name = "id")
    var id: Int,

    @Json(name = "adult")
    var adult: Boolean?,

    @Json(name = "backdrop_path")
    var backdropPath: String?,

    @Json(name = "original_language")
    var originalLanguage: String?,

    @Json(name = "original_title")
    var originalTitle: String?,

    @Json(name = "genre_ids")
    var genreIds: List<Int>?,

    @Json(name = "title")
    var title: String?,

    @Json(name = "vote_average")
    var voteAverage: Double?,

    @Json(name = "overview")
    var overview: String?,

    @Json(name = "release_date")
    var releaseDate: String?
)

/**
 * Convert Network results to database objects
 */
fun MovieListResponse.asLocalModel(movieType: MovieType): List<Movie> {
    return results.map {
        Movie(
            title = it.title,
            backdropPath = it.backdropPath,
            id = it.id,
//            genres = it.genreIds,
            originalTitle = it.originalTitle,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            type = movieType.string
        )
    }
}