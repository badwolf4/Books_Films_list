package com.gohool.booksfilmslist.ui.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gohool.booksfilmslist.BooksFilmsApplication
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.ui.MainActivity
import com.gohool.booksfilmslist.adapters.FilmsAdapter
import com.gohool.booksfilmslist.data.Constants
import com.gohool.booksfilmslist.data.films.Film
import com.gohool.booksfilmslist.data.films.FilmsDatabase
import com.gohool.booksfilmslist.data.films.FilmsRepository
import com.gohool.booksfilmslist.viewModels.FilmsViewModel
import com.gohool.booksfilmslist.viewModels.FilmsViewModelFactory
import kotlinx.android.synthetic.main.films_fragment.*
import kotlinx.android.synthetic.main.films_fragment.view.floating_add_btn


class FilmsFragment() : Fragment() {


    lateinit var films : LiveData<List<Film>>

    val database by lazy { FilmsDatabase.getInstance(this.requireActivity().applicationContext) }
    val repository by lazy { FilmsRepository(database.filmsDao()) }

    private val viewModel: FilmsViewModel by activityViewModels {
        FilmsViewModelFactory(repository)
    }

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
        view.floating_add_btn.setOnClickListener{
//            comunicator.nextFragment(Constants.FILM_EDIT_FRAGMENT)
            val action = FilmsFragmentDirections.actionFilmsFragmentToFilmEditFragment(-1)
            findNavController().navigate(action)

        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        films = viewModel.allFilms
        films.observe(viewLifecycleOwner, {
            Log.d("MyLog: ","Film list updated")
            if(it.isNotEmpty()){
                films_recycler_view.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = FilmsAdapter(it, viewModel)
                }

                Log.d("MyLog: ","Film list updated")
            }
        })

    }

    companion object {
        fun newInstance() : FilmsFragment =
            FilmsFragment()
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
