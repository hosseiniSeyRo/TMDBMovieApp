package com.parsdroid.tmdbmovieapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int): MovieListResponse
}
