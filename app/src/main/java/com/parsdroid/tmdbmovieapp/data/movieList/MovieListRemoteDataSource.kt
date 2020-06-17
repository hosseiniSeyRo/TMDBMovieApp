package com.parsdroid.tmdbmovieapp.data.movieList

import com.parsdroid.tmdbmovieapp.data.Result
import com.parsdroid.tmdbmovieapp.data.TMDBApi
import com.parsdroid.tmdbmovieapp.data.appModel.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieListRemoteDataSource(val tmdbApi: TMDBApi) {

    suspend fun getPopularMovie(page: Int): Result<List<Movie>> {
        Result.Success(tmdbApi.getPopularMovie(page))

        return try {
            withContext(Dispatchers.IO) {
                Result.Success(
                    tmdbApi.getPopularMovie(page).body()!!.toMovies()
                )
            }
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    private fun MovieListResponse.toMovies(): List<Movie> = results.map {
        Movie(
            it.backdropPath,
            it.genreIds,
            it.id,
            it.originalTitle,
            it.overview,
            it.popularity,
            it.posterPath,
            it.releaseDate,
            it.title,
            it.voteAverage,
            it.voteCount
        )
    }

}


