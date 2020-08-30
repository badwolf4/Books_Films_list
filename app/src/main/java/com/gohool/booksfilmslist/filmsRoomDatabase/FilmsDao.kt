package com.gohool.booksfilmslist.filmsRoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FilmsDao {

    @Insert
    fun insert(film: Film)

    @Delete
    fun delete(film: Film)

    @Update
    fun update(film: Film)

    @Query("SELECT * FROM films_table")
    fun getAll() : LiveData<List<Film>>
}