package com.gohool.booksfilmslist.data.books

import android.content.ContentValues
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class BookRepository(private val bookDao: BookDao) {
    val allBooks: Flow<List<Book>> = bookDao.getAll()

    @WorkerThread
    suspend fun insertBook(book: Book) = bookDao.insert(book)

    @WorkerThread
    suspend fun updateBook(book: Book) = bookDao.update(book)

    @WorkerThread
    suspend fun deleteBook(book: Book) = bookDao.delete(book)

    @WorkerThread
    fun getBookById(id: Int): Flow<Book> = bookDao.getFilm(id)
}