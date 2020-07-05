package com.parsdroid.tmdbmovieapp.data.db

import androidx.lifecycle.LiveData
import com.parsdroid.tmdbmovieapp.data.db.dao.MovieDao
import com.parsdroid.tmdbmovieapp.data.db.entity.Movie
import com.parsdroid.tmdbmovieapp.data.db.entity.MovieType

import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    val movies: LiveData<List<Movie>> = movieDao.getMoviesByType(MovieType.Popular.string)

    suspend fun insert(movies: List<Movie>) {
        movieDao.insert(movies)
    }
}
