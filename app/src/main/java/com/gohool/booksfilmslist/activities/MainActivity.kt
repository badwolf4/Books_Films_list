package com.gohool.booksfilmslist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gohool.booksfilmslist.Comunicator
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.adapters.BookDataBaseHelper
import com.gohool.booksfilmslist.classes.Film
import com.gohool.booksfilmslist.fragments.*

class MainActivity : AppCompatActivity(), Comunicator {

    val manager = supportFragmentManager

    override fun nextFragment(id: Int) {
        when(id)
        {
            R.id.filmsButton -> showFilmsFragment()
            R.id.booksButton -> showBooksFragment()
            R.id.floating_add_btn -> showAddBookFragment()
            R.id.addFilmButton -> showAddFilmFragment()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext, "This is toast", Toast.LENGTH_SHORT).show()
        showStartFragment()

        val bookDbHelper = BookDataBaseHelper(applicationContext)
        val bookDb = bookDbHelper.writableDatabase

    }

    fun showStartFragment()
    {
        val transaction = manager.beginTransaction()
        val fragment = StartFragment()
        transaction.replace(R.id.main_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showBooksFragment()
    {
        val transaction = manager.beginTransaction()
        val fragmet = BooksFragmet()
        transaction.replace(R.id.main_fragment, fragmet)
        transaction.addToBackStack(null)          //TODO co to znaczy, zmodyfikowanie tej linijki
        transaction.commit()
    }

    fun showFilmsFragment()
    {
        val transaction = manager.beginTransaction()
        val fragmet = FilmsFragment()
        transaction.replace(R.id.main_fragment, fragmet)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showAddBookFragment()
    {
        val transaction = manager.beginTransaction()
        val fragmet = BookEditFragment()
        transaction.replace(R.id.main_fragment, fragmet)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun showAddFilmFragment()
    {
        val transaction = manager.beginTransaction()
        val fragmet = AddFilmFragment()
        transaction.replace(R.id.main_fragment, fragmet)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun nextDetailedBookItemFragment(bundle: Bundle) {
        val transaction = manager.beginTransaction()
        val fragment = BookDetailedFragment()
//        val bundle = Bundle()
//
//        bundle.putString("tittle", book.tittle)
//        bundle.putString("author",book.author)
//        bundle.putString("description",book.description)
//        bundle.putString("type",book.type)
//        bundle.putInt("priority",book.priority)

        fragment.arguments = bundle
        //fragmet.onBookSet("Harry Potter")
        transaction.replace(R.id.main_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun nextDetailedFilmItemFragment(film: Film) {
        val transaction = manager.beginTransaction()
        val fragment = FilmDetailedFragment()
        val bundle = Bundle()

        bundle.putString("tittle", film.tittle)
        bundle.putInt("year", film.year)
        bundle.putInt("priority", film.priority)
        bundle.putString("type", film.type)
        bundle.putString("description", film.descriptiont)

        fragment.arguments = bundle
        transaction.replace(R.id.main_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun nextEditFragment(bundle: Bundle) {
        val transaction = manager.beginTransaction()
        val fragment = BookEditFragment()
        //Toast.makeText(applicationContext, "Book to edit: "+ bundle.getInt("bookId"),Toast.LENGTH_SHORT).show()
        fragment.arguments = bundle
        //fragmet.onBookSet("Harry Potter")
        transaction.replace(R.id.main_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun nextDeletedBook(position: Int) {
        val transaction = manager.beginTransaction()
        val fragment = BooksFragmet()
        val bundle = Bundle()
        bundle.putInt("position", position)
        fragment.arguments = bundle
        transaction.replace(R.id.main_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
