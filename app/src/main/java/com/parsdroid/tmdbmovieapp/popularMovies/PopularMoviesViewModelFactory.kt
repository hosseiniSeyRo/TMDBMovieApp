package com.parsdroid.tmdbmovieapp.popularMovies

import com.parsdroid.tmdbmovieapp.BaseViewModelFactory
import com.parsdroid.tmdbmovieapp.data.movieList.MovieListRepo
import javax.inject.Inject

class PopularMoviesViewModelFactory @Inject constructor(
    private val movieListRepo: MovieListRepo
) : BaseViewModelFactory<PopularMoviesViewModel>(
    PopularMoviesViewModel::class.java,
    { PopularMoviesViewModel(movieListRepo) })