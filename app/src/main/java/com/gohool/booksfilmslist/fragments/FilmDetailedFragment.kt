package com.gohool.booksfilmslist.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import com.gohool.booksfilmslist.Comunicator

import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.classes.Constants

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
                year?.let { bundle.putInt("year", it) }
                priority?.let { bundle.putInt("priority", it) }
                //Toast.makeText(context, item.tittle, Toast.LENGTH_LONG).show()
                comunicator.nextFragment(Constants.FILM_EDIT_FRAGMENT, bundle)
            }
            R.id.delete_button->{
                //deleting item
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
