package com.gohool.booksfilmslist.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController

import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.ui.MainActivity
import com.gohool.booksfilmslist.data.Constants
import com.gohool.booksfilmslist.data.films.Film
import com.gohool.booksfilmslist.data.films.FilmsDatabase
import com.gohool.booksfilmslist.data.films.FilmsRepository
import com.gohool.booksfilmslist.databinding.FragmentFilmDetailedBinding
import com.gohool.booksfilmslist.viewModels.FilmsViewModel
import com.gohool.booksfilmslist.viewModels.FilmsViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class FilmDetailedFragment : Fragment() {

    lateinit var film : LiveData<Film>

    val database by lazy { FilmsDatabase.getInstance(this.requireActivity().applicationContext) }
    val repository by lazy { FilmsRepository(database.filmsDao()) }

    private val viewModel: FilmsViewModel by activityViewModels {
        FilmsViewModelFactory(repository)
    }

    private lateinit var binding: FragmentFilmDetailedBinding

    var filmId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_film_detailed, container, false)

        arguments?.let {
            filmId = it.getInt("filmid")
            Log.d("FilmsIdPass:", filmId.toString())
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFilmDetailedBinding.bind(view)

        filmId?.let {
            film = viewModel.getFilmById(it)
            film.observe(viewLifecycleOwner,{

                binding.apply {
                    tittleDetailed.text = it.tittle
                    yearDetailed.text = it.year.toString()
                    typeDetailed.text = it.type
                    descriptionDetailed.text = it.description
                    priorityDetailed.text = it.priority.toString()
                }
            })

        }
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
                filmId?.let {
                    val action = FilmDetailedFragmentDirections.actionFilmDetailedFragmentToFilmEditFragment(it)
                    view?.findNavController()?.navigate(action)
                }
            }
            R.id.delete_button-> {
                var alertDialog = AlertDialog.Builder(context)
                    .setMessage("Are you sure that you want to delete?")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                        film.value?.let { viewModel.deleteFilm(it) }
                        view?.findNavController()?.navigateUp()
                    }).setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> })
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
