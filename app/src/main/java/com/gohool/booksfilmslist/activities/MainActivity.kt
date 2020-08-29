package com.gohool.booksfilmslist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gohool.booksfilmslist.Comunicator
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.adapters.BookDataBaseHelper
import com.gohool.booksfilmslist.classes.Constants
import com.gohool.booksfilmslist.classes.Film
import com.gohool.booksfilmslist.fragments.*

class MainActivity : AppCompatActivity(), Comunicator {

    val manager = supportFragmentManager

    override fun nextFragment(c: Constants) {
        val transaction = manager.beginTransaction()
        val fragment : Fragment
        when(c)
        {
            Constants.FILMS_FRAGMENT -> {
                fragment = FilmsFragment()
                transaction.replace(R.id.main_fragment, fragment)
                //showFilmsFragment()
            }
            Constants.BOOKS_FRAGMENT -> {
                fragment = BooksFragmet()
                transaction.replace(R.id.main_fragment, fragment)
                //showBooksFragment()
            }
            Constants.BOOK_EDIT_FRAGMENT->{
                fragment = BookEditFragment()
                transaction.replace(R.id.main_fragment, fragment)
            }
            Constants.FILM_EDIT_FRAGMENT->{
                fragment = FilmEditFragment()
                transaction.replace(R.id.main_fragment, fragment)
            }
            else -> {
                Toast.makeText(baseContext,"Error in changing fragments",Toast.LENGTH_SHORT).show()
                return
            }
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun nextFragment(c: Constants, bundle: Bundle) {
        val transaction = manager.beginTransaction()
        val fragment : Fragment
        when(c){
            Constants.FILM_EDIT_FRAGMENT->{
                fragment = FilmEditFragment()
                fragment.arguments = bundle
                transaction.replace(R.id.main_fragment, fragment)

            }
            Constants.BOOK_EDIT_FRAGMENT->{
                fragment = BookEditFragment()
                fragment.arguments = bundle
                transaction.replace(R.id.main_fragment, fragment)
            }
            Constants.FILM_DETAILED_FRAGMENT->{
                fragment = FilmDetailedFragment()
                fragment.arguments = bundle
                transaction.replace(R.id.main_fragment, fragment)
            }

            Constants.BOOK_DETAILED_FRAGMENT->{
                fragment = BookDetailedFragment()
                fragment.arguments = bundle
                transaction.replace(R.id.main_fragment, fragment)
            }

            else -> {
                Toast.makeText(baseContext,"Error in changing fragments",Toast.LENGTH_SHORT).show()
                return
            }
        }
        transaction.addToBackStack(null)
        transaction.commit()
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


}
