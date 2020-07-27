package com.gohool.booksfilmslist.fragments

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view: View = inflater.inflate(R.layout.books_fragmet, container, false)
        comunicator = activity as Comunicator

        view.findBookButton.setOnClickListener{
            Toast.makeText(context, "Find pressed", Toast.LENGTH_LONG).show()
        }
        view.floating_add_btn.setOnClickListener{
            comunicator.nextFragment(R.id.floating_add_btn)
        }
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbHelper = BookDataBaseHelper(view.context)

//        recycler_view_books.apply {
//            layoutManager = LinearLayoutManager(activity)
//            //val dbHelper = BookDataBaseHelper(context)
//            val bookList = dbHelper.getBooks()
//            adapter = BooksAdapter(this@BooksFragmet, bookList)
//        }

        viewBooks()
//        arguments?.getInt("id").let{
//            if (it != null) {
//                Toast.makeText(context, "Book id: ${it.toString()}", Toast.LENGTH_SHORT)
//                adapter.removeBook(it)
//            }
//        }
    }

    override fun onResume() {
        viewBooks()
        super.onResume()
    }

    private fun viewBooks(){
        //val bookList = dbHelper.getBooks()
        recycler_view_books.apply {
            layoutManager = LinearLayoutManager(activity)
            //val dbHelper = BookDataBaseHelper(context)
            val bookList = dbHelper.getBooks()
            adapter = BooksAdapter(this@BooksFragmet, bookList)
        }
    }

    companion object{
        lateinit var adapter : BooksAdapter
        lateinit var dbHelper: BookDataBaseHelper
        fun newInstance(): BooksFragmet =
            BooksFragmet()

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




//    override fun onDestroy() {
//        super.onDestroy()
//        db.close()
//    }

}

//list.remove(position);
//recycler.removeViewAt(position);
//mAdapter.notifyItemRemoved(position);
//mAdapter.notifyItemRangeChanged(position, list.size());

//mAdapter.notifyDataSetChanged();










