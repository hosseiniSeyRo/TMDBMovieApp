package com.parsdroid.tmdbmovieapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<MovieListResponse>
}
