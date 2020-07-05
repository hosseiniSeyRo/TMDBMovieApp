package com.parsdroid.tmdbmovieapp.di

import android.app.Application
import androidx.room.Room
import com.parsdroid.tmdbmovieapp.MyApp
import com.parsdroid.tmdbmovieapp.data.db.TMDBDataBase
import com.parsdroid.tmdbmovieapp.data.db.dao.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    fun provideApp(): Application = MyApp.instance

    @Singleton
    @Provides
    fun provideDb(app: Application): TMDBDataBase {
        return Room
            .databaseBuilder(app, TMDBDataBase::class.java, "tmdbMovie.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: TMDBDataBase): MovieDao {
        return db.movieDao()
    }
}