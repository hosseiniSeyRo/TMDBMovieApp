package com.parsdroid.tmdbmovieapp.data.api

import com.parsdroid.tmdbmovieapp.data.db.entity.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val tmdbService: TMDBService) {

    suspend fun getMovies(movieType: MovieType, page: Int): Response<MovieListResponse> {
        return withContext(Dispatchers.IO) {
            when (movieType) {
                MovieType.Popular -> tmdbService.getPopularMovies(page)
                MovieType.TopRated -> TODO()
                MovieType.NowPlaying -> TODO()
                MovieType.Upcoming -> TODO()
            }
        }
    }
}


