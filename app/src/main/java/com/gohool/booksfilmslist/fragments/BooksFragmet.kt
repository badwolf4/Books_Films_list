package com.gohool.booksfilmslist.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gohool.booksfilmslist.Comunicator
import com.gohool.booksfilmslist.adapters.BooksAdapter
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.adapters.onBookItemClickListener
import com.gohool.booksfilmslist.classes.Book
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.books_fragmet.*


class BooksFragmet : onBookItemClickListener, Fragment() {

    val books = listOf(
        Book("Kult", "Autor", "Gatunek", 2, "none"),
        Book(
            "Sztuka milosci",
            "Autor",
            "Gatunek",
            2,
            "none"
        ),
        Book(
            "13 reasons why",
            "Autor",
            "Gatunek",
            2,
            "none"
        ),
        Book(
            "13 reasons why",
            "Autor",
            "Gatunek",
            2,
            "none"
        ),
        Book(
            "13 reasons why",
            "Autor",
            "Gatunek",
            2,
            "none"
        ),
        Book(
            "13 reasons why",
            "Autor",
            "Gatunek",
            2,
            "none"
        ),
        Book(
            "13 reasons why",
            "Autor",
            "Gatunek",
            2,
            "none"
        ),
        Book(
            "13 reasons why",
            "Autor",
            "Gatunek",
            2,
            "none"
        ),
        Book(
            "13 reasons why",
            "Autor",
            "Gatunek",
            2,
            "none"
        ),
        Book(
            "wo ist sinna",
            "Autor",
            "Gatunek",
            2,
            "none"
        ),
        Book("Tytul", "Autor", "Gatunek", 2,"none")

    )

    lateinit var comunicator: Comunicator


    //val books = listOf<String>("Kult", "Sztuka milosci", "13 reasons why", "wo ist sinna")

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
        return view
    }


        //recyclerViewBooks.layoutManager = LinearLayoutManager(activity)

        //recyclerViewBooks.adapter = BooksAdapter(books)

        //return view

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_books.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = BooksAdapter(books,this@BooksFragmet)
        }
    }

    companion object{
        fun newInstance(): BooksFragmet =
            BooksFragmet()
    }

    override fun onItemClick(item: Book, position: Int) {
        comunicator.nextDetailedBookItemFragment(item)
    }

}












