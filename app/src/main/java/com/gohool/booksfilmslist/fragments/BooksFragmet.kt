package com.gohool.booksfilmslist.fragments

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gohool.booksfilmslist.Comunicator
import com.gohool.booksfilmslist.adapters.BooksAdapter
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.adapters.BookDataBaseHelper
import com.gohool.booksfilmslist.adapters.onBookItemClickListener
import com.gohool.booksfilmslist.classes.Book
import kotlinx.android.synthetic.main.books_fragmet.*
import kotlinx.android.synthetic.main.books_fragmet.view.*
import java.text.FieldPosition


class BooksFragmet : onBookItemClickListener, Fragment() {


    lateinit var comunicator: Comunicator
    var bookList = ArrayList<Book>()

    companion object{
        lateinit var adapter : BooksAdapter
        lateinit var dbHelper: BookDataBaseHelper
        fun newInstance(): BooksFragmet =
            BooksFragmet()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view: View = inflater.inflate(R.layout.books_fragmet, container, false)
        comunicator = activity as Comunicator

        view.floating_add_btn.setOnClickListener{
            comunicator.nextFragment(R.id.floating_add_btn)
        }
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = BookDataBaseHelper(view.context)
        bookList = dbHelper.getBooks()
        viewBooks(bookList)
    }

    override fun onResume() {
        (bookList)
        super.onResume()
    }

    private fun viewBooks(newList : ArrayList<Book>){
        recycler_view_books.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = BooksAdapter(this@BooksFragmet, newList)
        }
    }


    override fun onItemClick(book: Book, position: Int) {
        val bundle = Bundle()


        bundle.putString("tittle", book.tittle)
        bundle.putString("author",book.author)
        bundle.putString("description",book.description)
        bundle.putString("type",book.type)
        bundle.putInt("priority",book.priority)
        bundle.putInt("bookId", position)


        comunicator.nextDetailedBookItemFragment(bundle)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_option_menu, menu)

        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var filteredBooks = ArrayList<Book>()

                if(!newText.isNullOrEmpty()){
                    val text = newText.toLowerCase()
                    var book : Book

                    for(i in 0 until bookList.size-1){
                        book = bookList.get(i)
                        if( book.tittle.toLowerCase().contains(text)
                            or book.author.toLowerCase().contains(text)
                            or book.type.toLowerCase().contains(text)){

                            filteredBooks.add(bookList.get(i))
                        }
                    }
                }
                else{
                    filteredBooks=bookList
                }
                viewBooks(filteredBooks)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}












