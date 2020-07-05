package com.parsdroid.tmdbmovieapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey(autoGenerate = false) var id: Int,
    @ColumnInfo(name = "backdropPath") var backdropPath: String?,
//    var genres: List<Int>?,
    var originalTitle: String?,
    var overview: String?,
    var popularity: Double?,
    var posterPath: String?,
    var releaseDate: String?,
    var title: String?,
    var voteAverage: Double?,
    var voteCount: Int?,
    var type: String?
)

enum class MovieType(val string: String) {
    Popular("Popular"),
    TopRated("TopRated"),
    NowPlaying("NowPlaying"),
    Upcoming("Upcoming")
}