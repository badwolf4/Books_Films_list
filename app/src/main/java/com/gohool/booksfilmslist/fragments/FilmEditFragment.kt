package com.gohool.booksfilmslist.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.gohool.booksfilmslist.Comunicator
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.activities.MainActivity
import com.gohool.booksfilmslist.classes.Constants
import com.gohool.booksfilmslist.filmsRoomDatabase.Film
import kotlinx.android.synthetic.main.fragment_add_book.view.*
import kotlinx.android.synthetic.main.fragment_add_film.view.*
import org.w3c.dom.Text


class FilmEditFragment : Fragment() {

    lateinit var priority : SeekBar
    lateinit var priorityValue : TextView
    lateinit var comunicator: Comunicator

    var filmId : Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        comunicator = activity as Comunicator
        Log.d("MyLog: ","Film edit fragment created")

        val view : View = inflater.inflate(R.layout.fragment_add_film, container, false)
        priority = view.findViewById(R.id.priority) as SeekBar
        priorityValue = view.findViewById(R.id.priority_value) as TextView

        arguments?.getString("tittle").let{
            view.filmTittle.setText(it)
        }
        arguments?.getString("type").let{
            view.filmType.setText(it)
        }
        arguments?.getString("description").let {
            view.filmDescription.setText(it)
        }
        Log.v("MyLog","description set")

        arguments?.getInt("year").let {
            if (it != null) {
                view.filmYear.setText(it.toString())
            }
        }

        filmId = arguments?.getInt("filmId")

        Log.v("MyLog","year set")

        arguments?.getInt("priority").let {
            if (it != null) {
                priority.setProgress(it,false)
            }
            priorityValue.text = it.toString()
        }
        if(arguments?.getInt("priority")==null)
            priorityValue.text = "0"
        Log.v("MyLog","priority set")

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_item_opion_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("MyLog", "Save item clicked")
        //saving updated item
        val tittle = view?.filmTittle?.text.toString()
        Log.d("MyLog: ", "Tittle saved")
        val type = view?.filmType?.text.toString()
        Log.d("MyLog: ", "Type saved")
        val year = view?.filmYear?.text.toString().toInt()
        Log.d("MyLog: ", "Year saved")
        val mpriority = priority.progress
        Log.d("MyLog: ", "Priority saved")
        val descrioption = view?.filmDescription?.text.toString()
        Log.d("MyLog: ", "Values are saved")

        if(filmId==null){
            val film = Film(tittle,type,year,mpriority,descrioption)
            MainActivity.viewModel.insertFilm(film)
            Log.v("MyLog", "Film inserted")

        }
        else{
            val film = Film(tittle,type,year,mpriority,descrioption)
            film.filmId = filmId as Int
            MainActivity.viewModel.updateFilm(film)
            Log.v("MyLog", "Film updated")
        }
        comunicator.nextFragment(Constants.FILMS_FRAGMENT)

        return super.onOptionsItemSelected(item)
    }


}