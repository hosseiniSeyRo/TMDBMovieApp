package com.parsdroid.tmdbmovieapp.ui.popularMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parsdroid.tmdbmovieapp.data.api.MovieListResponse
import com.parsdroid.tmdbmovieapp.data.db.entity.Movie
import com.parsdroid.tmdbmovieapp.data.repository.MovieRepository
import com.parsdroid.tmdbmovieapp.ui.ResponseState
import kotlinx.coroutines.launch
import java.io.IOException

class PopularMoviesViewModel(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _loadMoviesState = MutableLiveData<ResponseState<List<Movie>>>()
    val loadMoviesState: LiveData<ResponseState<List<Movie>>> = _loadMoviesState

    val items: MutableList<Movie> = mutableListOf()

    var nextPage: Int = 1
    private var totalPage: Long = 1
    lateinit var errorMessage: String
        private set

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        if (nextPage <= totalPage) {
            _loadMoviesState.value = ResponseState.Loading
            viewModelScope.launch {
                try {
                    val result = movieRepository.getPopularMovie(nextPage)
                    _loadMoviesState.value = ResponseState.Success(result.toMovies())
                    items.addAll(result.toMovies())
                    nextPage++
                    totalPage = result.totalPages
                } catch (e: IOException) {
                    //TODO (handle errors)
                    _loadMoviesState.value = ResponseState.Error(e)
                    errorMessage = e.message.toString()
                }
            }
        }
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
