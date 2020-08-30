package com.gohool.booksfilmslist.fragments


import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gohool.booksfilmslist.Comunicator
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.activities.MainActivity
import com.gohool.booksfilmslist.adapters.FilmsAdapter
import com.gohool.booksfilmslist.adapters.onFilmItemClickListener
import com.gohool.booksfilmslist.classes.Constants
import com.gohool.booksfilmslist.filmsRoomDatabase.Film
import com.gohool.booksfilmslist.filmsRoomDatabase.FilmsDatabase
import kotlinx.android.synthetic.main.films_fragment.*
import kotlinx.android.synthetic.main.films_fragment.view.floating_add_btn


class FilmsFragment :  onFilmItemClickListener, Fragment() {

//    val films = listOf(
//        Film("Lol", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none"),
//        Film("Perfect Strangers", "drama", 2008, 5,"none")
//
//    )

    lateinit var films : LiveData<List<Film>>

    lateinit var comunicator: Comunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
        //films=MainActivity.viewModel.getAllFilms()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.films_fragment, container, false)
        comunicator = activity as Comunicator
        view.floating_add_btn.setOnClickListener{
            comunicator.nextFragment(Constants.FILM_EDIT_FRAGMENT)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        films = MainActivity.viewModel.getAllFilms()
        films.observe(viewLifecycleOwner, Observer {
            Log.d("MyLog: ","Film list updated")
            if(it.isNotEmpty()){
                films_recycler_view.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = FilmsAdapter(it,this@FilmsFragment)

                }
                Log.d("MyLog: ","Film list updated")
            }
        })

    }

    companion object {
        fun newInstance() : FilmsFragment =
            FilmsFragment()
    }

    override fun onItemClick(item: Film, position: Int) {
        val bundle = Bundle()

        bundle.putString("tittle", item.tittle)
        bundle.putString("description", item.description)
        bundle.putString("type", item.type)
        bundle.putInt("year", item.year)
        bundle.putInt("priority", item.priority)
        bundle.putInt("filmId", item.filmId)
        //Toast.makeText(context, item.tittle, Toast.LENGTH_LONG).show()
        comunicator.nextFragment(Constants.FILM_DETAILED_FRAGMENT, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_option_menu, menu)
        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var filteredFilms = ArrayList<Film>()

                if(!newText.isNullOrEmpty()){
                    val text = newText.toLowerCase()
                    var film : Film

                    //sortowanie

//                    for(i in 0 until films.size){
//                        film = films.get(i)
//                        if( film.tittle.toLowerCase().contains(text)
//                            or film.type.toLowerCase().contains(text)){
//
//                            filteredFilms.add(films.get(i))
//                        }
//                    }
                }
                else{
                    //filteredFilms=films
                }
                //viewBooks(filteredBooks)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}
