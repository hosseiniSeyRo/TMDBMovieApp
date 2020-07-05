package com.parsdroid.tmdbmovieapp.data.repository

import androidx.lifecycle.LiveData
import com.parsdroid.tmdbmovieapp.data.api.MovieRemoteDataSource
import com.parsdroid.tmdbmovieapp.data.api.asLocalModel
import com.parsdroid.tmdbmovieapp.data.db.MovieLocalDataSource
import com.parsdroid.tmdbmovieapp.data.db.entity.Movie
import com.parsdroid.tmdbmovieapp.data.db.entity.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) {

    val movies: LiveData<List<Movie>> = movieLocalDataSource.movies

    suspend fun getMovies(movieType: MovieType, page: Int) {
        withContext(Dispatchers.IO) {
            //fetch from network
            val remoteData = movieRemoteDataSource.getMovies(movieType, page)

            //save in DB
            remoteData.body()?.let {
                movieLocalDataSource.insert(it.asLocalModel(movieType))
            }
        }
    }
}