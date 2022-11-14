package ru.kosteloff.moviegoer.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.kosteloff.moviegoer.model.Result

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies_table")
    fun getAllMovies(): LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Result)

    @Delete
    suspend fun delete(movie: Result)
}