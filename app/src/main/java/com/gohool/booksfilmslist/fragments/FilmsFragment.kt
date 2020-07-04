package com.gohool.booksfilmslist.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gohool.booksfilmslist.Comunicator
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.adapters.FilmsAdapter
import com.gohool.booksfilmslist.adapters.onFilmItemClickListener
import com.gohool.booksfilmslist.classes.Film
import kotlinx.android.synthetic.main.films_fragment.*
import kotlinx.android.synthetic.main.films_fragment.view.*


class FilmsFragment :  onFilmItemClickListener, Fragment() {

    val films = listOf(
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none"),
        Film("Perfect Strangers", "drama", 2008, 5,"none")

    )

    lateinit var comunicator: Comunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.films_fragment, container, false)
        comunicator = activity as Comunicator

        view.addFilmButton.setOnClickListener {
            Toast.makeText(context, "Add pressed", Toast.LENGTH_LONG).show()
            comunicator.nextFragment(R.id.addFilmButton)
        }

        view.findFilmButton.setOnClickListener {
            Toast.makeText(context, "Find pressed", Toast.LENGTH_LONG).show()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        films_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = FilmsAdapter(films,this@FilmsFragment)
        }

    }

    companion object {
        fun newInstance() : FilmsFragment =
            FilmsFragment()
    }

    override fun onItemClick(item: Film, position: Int) {
        Toast.makeText(context, item.tittle, Toast.LENGTH_LONG).show()
        comunicator.nextDetailedFilmItemFragment(item)
    }

}
