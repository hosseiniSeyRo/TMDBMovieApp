package com.parsdroid.tmdbmovieapp.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(val tmdbService: TMDBService) {

    suspend fun getPopularMovie(page: Int): MovieListResponse {
        return withContext(Dispatchers.IO) {
            tmdbService.getPopularMovie(page)
        }
    }
}


