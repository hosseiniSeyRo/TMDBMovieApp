package com.parsdroid.tmdbmovieapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.parsdroid.tmdbmovieapp.data.db.dao.MovieDao
import com.parsdroid.tmdbmovieapp.data.db.entity.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class TMDBDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}