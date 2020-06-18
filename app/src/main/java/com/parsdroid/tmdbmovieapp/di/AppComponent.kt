package com.parsdroid.tmdbmovieapp.di

import com.parsdroid.tmdbmovieapp.popularMovies.PopularMoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface AppComponent {

    fun inject(fragment: PopularMoviesFragment)
}