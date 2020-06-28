package com.parsdroid.tmdbmovieapp.data.repository

import com.parsdroid.tmdbmovieapp.data.api.MovieListResponse
import com.parsdroid.tmdbmovieapp.data.api.MovieRemoteDataSource
import javax.inject.Inject


class MovieRepository @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource) {

    suspend fun getPopularMovie(page: Int): MovieListResponse =
        movieRemoteDataSource.getPopularMovie(page)
}