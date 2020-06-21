package com.parsdroid.tmdbmovieapp.data.movieList

import com.parsdroid.tmdbmovieapp.data.TMDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieListRemoteDataSource @Inject constructor(val tmdbApi: TMDBApi) {

    suspend fun getPopularMovie(page: Int): MovieListResponse {
        return withContext(Dispatchers.IO) {
            tmdbApi.getPopularMovie(page)
        }
    }
}


