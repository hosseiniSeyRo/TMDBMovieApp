package com.parsdroid.tmdbmovieapp.ui.popularMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parsdroid.tmdbmovieapp.data.db.entity.Movie
import com.parsdroid.tmdbmovieapp.data.db.entity.MovieType
import com.parsdroid.tmdbmovieapp.data.repository.MovieRepository
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _loadMoviesState = MutableLiveData<RecyclerViewState>()
    val loadMoviesState: LiveData<RecyclerViewState> = _loadMoviesState

    val items: LiveData<List<Movie>> = movieRepository.movies
    val movieType = MovieType.Popular
    lateinit var errorMessage: String
        private set

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        _loadMoviesState.value = RecyclerViewState.LOADING
        viewModelScope.launch {
            try {
                movieRepository.getMovies(movieType, 1)
                _loadMoviesState.value = RecyclerViewState.SUCCESS
            } catch (e: IOException) {
                //TODO (handle errors)
                _loadMoviesState.value = RecyclerViewState.ERROR
                errorMessage = e.message.toString()
            }
        }
    }
}