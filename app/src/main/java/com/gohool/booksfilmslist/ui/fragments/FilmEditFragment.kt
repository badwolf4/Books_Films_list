package com.gohool.booksfilmslist.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.data.films.Film
import com.gohool.booksfilmslist.data.films.FilmsDatabase
import com.gohool.booksfilmslist.data.films.FilmsRepository
import com.gohool.booksfilmslist.databinding.FragmentAddFilmBinding
import com.gohool.booksfilmslist.viewModels.FilmsViewModel
import com.gohool.booksfilmslist.viewModels.FilmsViewModelFactory


class FilmEditFragment : Fragment() {

    lateinit var priority : SeekBar
    lateinit var priorityValue : TextView

    var film: LiveData<Film>? = null

    var filmId : Int?=null

    val database by lazy { FilmsDatabase.getInstance(this.requireActivity().applicationContext) }
    val repository by lazy { FilmsRepository(database.filmsDao()) }

    private val viewModel: FilmsViewModel by activityViewModels {
        FilmsViewModelFactory(repository)
    }

    private lateinit var binding: FragmentAddFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MyLog: ","Film edit fragment created")

        val view : View = inflater.inflate(R.layout.fragment_add_film, container, false)
        priority = view.findViewById(R.id.priority) as SeekBar
        priorityValue = view.findViewById(R.id.priority_value) as TextView

        arguments?.let {
            filmId = it.getInt("filmid")

            Log.d("FilmIdEditFragment:", filmId.toString())
            filmId?.let {  film = viewModel.getFilmById(it)
            Log.d("FilmReturned:", "tittle: ${film}")}
        }

        priority.max = 10

        priority.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                priorityValue.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                priorityValue.text = seekBar?.progress.toString()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                priorityValue.text = seekBar?.progress.toString()
            }

        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddFilmBinding.bind(view)
        film?.let {
            it.observe(viewLifecycleOwner,{
                it?.let {
                    Log.d("FilmsTittle:", it.tittle)
                    binding.filmTittle.setText(it.tittle)
                    binding.filmDescription.setText(it.description)
                    binding.filmType.setText(it.type)
                    binding.filmYear.setText(it.year.toString())
                    binding.priority.setProgress(it.priority)
                }
            })

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_item_opion_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val newFilm = createFilm()
        newFilm?.let {
            film?.value?.let {
                newFilm.filmId= filmId!!
                viewModel.updateFilm(newFilm)
                findNavController().navigateUp()
            } ?: kotlin.run {
                viewModel.insertFilm(newFilm)
                findNavController().navigateUp()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun createFilm(): Film?{
        binding.apply {
            if(!filmTittle.text.toString().isEmpty()
                && !filmDescription.text.toString().isEmpty()
                && !filmType.text.toString().isEmpty()
                && !(filmYear.text.toString().toInt()<=0))
            {
                return Film(filmTittle.text.toString(),
                    filmType.text.toString(),
                    filmYear.text.toString().toInt(),
                    priorityValue.text.toString().toInt(),
                    filmDescription.text.toString())
            }
            else
            {
                Toast.makeText(context,"Please fill all the fields", Toast.LENGTH_SHORT).show()
                return null
            }
        }
    }


}