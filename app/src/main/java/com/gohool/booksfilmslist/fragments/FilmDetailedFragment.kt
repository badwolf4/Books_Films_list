package com.gohool.booksfilmslist.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import com.gohool.booksfilmslist.Comunicator

import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.activities.MainActivity
import com.gohool.booksfilmslist.classes.Constants
import com.gohool.booksfilmslist.filmsRoomDatabase.Film

/**
 * A simple [Fragment] subclass.
 */
class FilmDetailedFragment : Fragment() {

    lateinit var comunicator : Comunicator

    var tittle : String? = null
    var type : String? = null
    var description : String? = null
    var year : Int? = null
    var priority : Int? = null
    var filmId : Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        comunicator = activity as Comunicator

        val view : View = inflater.inflate(R.layout.fragment_film_detailed, container, false)
        tittle = arguments?.getString("tittle")
        type = arguments?.getString("type")
        year = arguments?.getInt("year")
        description = arguments?.getString("description")
        priority = arguments?.getInt("priority")
        filmId = arguments?.getInt("filmId")

        val filmTittle = view.findViewById<TextView>(R.id.tittle_detailed)
        val filmYear = view.findViewById<TextView>(R.id.year_detailed)
        val filmType = view.findViewById<TextView>(R.id.type_detailed)
        val filmDescription = view.findViewById<TextView>(R.id.description_detailed)
        val filmPriority = view.findViewById<TextView>(R.id.priority_detailed)

        filmTittle.setText(tittle)
        filmYear.setText(year.toString())
        filmType.setText(type)
        filmDescription.setText(description)
        filmPriority.setText(priority.toString())
        Toast.makeText(context, tittle, Toast.LENGTH_SHORT).show()
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detailed_option_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val bundle = Bundle()
        val id = item.itemId
        when(id){
            R.id.edit_button->{
                bundle.putString("tittle", tittle)
                bundle.putString("description", description)
                bundle.putString("type", type)
                filmId?.let { bundle.putInt("filmId", it) }
                year?.let { bundle.putInt("year", it) }
                priority?.let { bundle.putInt("priority", it) }
                //Toast.makeText(context, item.tittle, Toast.LENGTH_LONG).show()
                comunicator.nextFragment(Constants.FILM_EDIT_FRAGMENT, bundle)
            }
            R.id.delete_button-> {
                var alertDialog = AlertDialog.Builder(context)
                    .setMessage("Are you sure that you want to delete ${tittle}?")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                        val film = Film(
                            tittle as String, type as String, year as Int,
                            priority as Int, description as String
                        )
                        film.filmId = filmId as Int
                        MainActivity.viewModel.deleteFilm(film)
                        comunicator.nextFragment(Constants.FILMS_FRAGMENT)
                    }).setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> })
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
