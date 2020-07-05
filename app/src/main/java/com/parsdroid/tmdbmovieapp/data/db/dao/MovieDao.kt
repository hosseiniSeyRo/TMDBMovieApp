package com.parsdroid.tmdbmovieapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.parsdroid.tmdbmovieapp.data.db.entity.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(movies: List<Movie>)

    @Query("DELETE from movie_table")
    suspend fun deleteAll()

    // TODO(edit query: add page, sort and limit. edit type)
    @Query("SELECT * FROM movie_table WHERE type LIKE :movieTypeString")
    fun getMoviesByType(movieTypeString: String): LiveData<List<Movie>>
}