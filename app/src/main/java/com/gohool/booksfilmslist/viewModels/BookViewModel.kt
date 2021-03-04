package com.gohool.booksfilmslist.viewModels

import androidx.lifecycle.*
import com.gohool.booksfilmslist.data.books.Book
import com.gohool.booksfilmslist.data.books.BookRepository
import com.gohool.booksfilmslist.data.films.FilmsRepository
import kotlinx.coroutines.launch

class BookViewModel(
    private val bookRepository: BookRepository
    ) : ViewModel() {

    val allBooks = bookRepository.allBooks.asLiveData()

    fun insertBook(book: Book) = viewModelScope.launch { bookRepository.insertBook(book) }

    fun updateBook(book: Book) = viewModelScope.launch { bookRepository.updateBook(book) }

    fun deleteBook(book: Book) = viewModelScope.launch { bookRepository.deleteBook(book) }

    fun getBookById(id: Int) : LiveData<Book> = bookRepository.getBookById(id).asLiveData()
}

class BooksViewModelFactory(private  val repository: BookRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BookViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
