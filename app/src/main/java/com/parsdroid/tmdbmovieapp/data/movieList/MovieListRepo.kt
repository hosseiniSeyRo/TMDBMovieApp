package com.parsdroid.tmdbmovieapp.data.movieList

import com.parsdroid.tmdbmovieapp.data.Result
import com.parsdroid.tmdbmovieapp.data.appModel.Movie
import javax.inject.Inject


class MovieListRepo @Inject constructor(private val movieListRemoteDataSource: MovieListRemoteDataSource) {

    suspend fun getPopularMovie(page: Int): Result<List<Movie>> =
        movieListRemoteDataSource.getPopularMovie(page)
}