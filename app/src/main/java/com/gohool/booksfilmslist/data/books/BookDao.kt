package com.gohool.booksfilmslist.data.books

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert
    suspend fun insert(book: Book)

    @Delete
    suspend fun delete(book: Book)

    @Update
    suspend fun update(book: Book)

    @Query("SELECT * FROM book_table")
    fun getAll() : Flow<List<Book>>

    @Query("SELECT * FROM book_table WHERE id = :id LIMIT 1")
    fun getFilm(id: Int): Flow<Book>

}