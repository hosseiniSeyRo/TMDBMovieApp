package com.parsdroid.tmdbmovieapp.popularMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parsdroid.tmdbmovieapp.data.Result
import com.parsdroid.tmdbmovieapp.data.appModel.Movie
import com.parsdroid.tmdbmovieapp.data.movieList.MovieListRepo
import kotlinx.coroutines.launch

class PopularMoviesViewModel(private val movieListRepo: MovieListRepo) :
    ViewModel() {

    private val _items = MutableLiveData<List<Movie>>()
    val items: LiveData<List<Movie>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _snackbarMessage = MutableLiveData<String>()
    val snackbarMessage: LiveData<String> = _snackbarMessage

    init {
        loadPopularMovies(1)
    }

    fun loadPopularMovies(page: Int) {
        _dataLoading.value = true
        viewModelScope.launch {
            computeResult(movieListRepo.getPopularMovie(page))
            _dataLoading.value = false
        }
    }

    private fun computeResult(movieListResult: Result<List<Movie>>) {
        if (movieListResult is Result.Success) {
            _items.value = movieListResult.data
        } else {
//            setSnackbarMessage(Resources.getSystem().getString(R.string.errorLoadingMovieList))
            setSnackbarMessage(movieListResult.toString())
        }
    }

    private fun setSnackbarMessage(message: String) {
        _snackbarMessage.value = message
    }
}