package com.gohool.booksfilmslist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.gohool.booksfilmslist.R

/**
 * A simple [Fragment] subclass.
 */
class FilmDetailedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_film_detailed, container, false)
        var tittle = arguments?.getString("tittle")
        var type = arguments?.getString("type")
        var year = arguments?.getInt("year")
        var description = arguments?.getString("description")
        var priority = arguments?.getInt("priority")

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

}
