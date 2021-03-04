package com.gohool.booksfilmslist.data.films

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gohool.booksfilmslist.data.films.Film
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmsDao {
    @Insert
    suspend fun insert(film: Film)

    @Delete
    suspend fun delete(film: Film)

    @Update
    suspend fun update(film: Film)

    @Query("SELECT * FROM films_table")
    fun getAll() : Flow<List<Film>>

    @Query("SELECT * FROM films_table WHERE filmId = :id LIMIT 1")
    fun getFilm(id: Int) : Flow<Film>


}