package com.parsdroid.tmdbmovieapp.data

import com.parsdroid.tmdbmovieapp.data.movieList.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {

    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int): MovieListResponse
}
