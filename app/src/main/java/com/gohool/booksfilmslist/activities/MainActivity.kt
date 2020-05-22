package com.gohool.booksfilmslist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gohool.booksfilmslist.Comunicator
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.classes.Book
import com.gohool.booksfilmslist.fragments.BookItemDetailedFragment
import com.gohool.booksfilmslist.fragments.BooksFragmet
import com.gohool.booksfilmslist.fragments.FilmsFragment
import com.gohool.booksfilmslist.fragments.StartFragment

class MainActivity : AppCompatActivity(), Comunicator {

    val manager = supportFragmentManager

    override fun nextFragment(id: Int) {
        when(id)
        {
            R.id.filmsButton -> showFilmsFragment()
            R.id.booksButton -> showBooksFragment()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext, "This is toast", Toast.LENGTH_SHORT).show()

        showStartFragment()

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

    override fun nextDetailedBookItemFragment(book: Book) {
        val transaction = manager.beginTransaction()
        val fragmet = BookItemDetailedFragment()
        val bundle = Bundle()

        bundle.putString("tittle", book.tittle)
        bundle.putString("author",book.author)
        bundle.putString("description",book.description)
        bundle.putString("type",book.type)
        bundle.putInt("priority",book.priority)

        fragmet.arguments = bundle
        //fragmet.onBookSet("Harry Potter")
        transaction.replace(R.id.main_fragment, fragmet)
        transaction.addToBackStack(null)
        transaction.commit()

    }

}
