package com.parsdroid.tmdbmovieapp.data.movieList

import javax.inject.Inject


class MovieListRepo @Inject constructor(private val movieListRemoteDataSource: MovieListRemoteDataSource) {

    suspend fun getPopularMovie(page: Int): MovieListResponse =
        movieListRemoteDataSource.getPopularMovie(page)
}