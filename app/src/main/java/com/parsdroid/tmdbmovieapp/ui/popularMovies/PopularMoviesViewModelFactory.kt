package com.parsdroid.tmdbmovieapp.ui.popularMovies

import com.parsdroid.tmdbmovieapp.data.movieList.MovieListRepo
import com.parsdroid.tmdbmovieapp.ui.BaseViewModelFactory
import javax.inject.Inject

class PopularMoviesViewModelFactory @Inject constructor(
    private val movieListRepo: MovieListRepo
) : BaseViewModelFactory<PopularMoviesViewModel>(
    PopularMoviesViewModel::class.java,
    { PopularMoviesViewModel(movieListRepo) })