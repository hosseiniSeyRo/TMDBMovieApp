package com.parsdroid.tmdbmovieapp.ui.popularMovies

import com.parsdroid.tmdbmovieapp.data.repository.MovieRepository
import com.parsdroid.tmdbmovieapp.ui.BaseViewModelFactory
import javax.inject.Inject

class PopularMoviesViewModelFactory @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModelFactory<PopularMoviesViewModel>(
    PopularMoviesViewModel::class.java,
    { PopularMoviesViewModel(movieRepository) })