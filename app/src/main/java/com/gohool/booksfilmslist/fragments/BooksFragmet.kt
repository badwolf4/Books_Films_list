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
    lateinit var db : SQLiteDatabase


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
        view.addBookButton.setOnClickListener{
            comunicator.nextFragment(R.id.addBookButton)
        }
        view.findBookButton.setOnClickListener{
            Toast.makeText(context, "Find pressed", Toast.LENGTH_LONG).show()
        }

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_books.apply {
            layoutManager = LinearLayoutManager(activity)
            val dbHelper = BookDataBaseHelper(context)
             db = dbHelper.writableDatabase
            adapter = BooksAdapter(this@BooksFragmet, db)

//            arguments?.getInt("position").let {
//                var position = it
//                if (it != null) {
//                    recycler_view_books.removeViewAt(it)
//
//                }
//            }


        }

    }

    companion object{
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










