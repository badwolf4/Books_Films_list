package com.gohool.booksfilmslist.viewHolders

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.data.films.Film
import com.gohool.booksfilmslist.data.films.FilmsDatabase
import com.gohool.booksfilmslist.data.films.FilmsRepository
import com.gohool.booksfilmslist.ui.fragments.FilmsFragmentDirections
import com.gohool.booksfilmslist.viewModels.FilmsViewModel
import com.gohool.booksfilmslist.viewModels.FilmsViewModelFactory
import kotlinx.android.synthetic.main.film_item.view.*

class FilmViewHolder(inflater: LayoutInflater,parent: ViewGroup ) :RecyclerView.ViewHolder(inflater.inflate(
    R.layout.film_item, parent, false)) {

    private var mTittleTextView: TextView? = null
    private var mYearTextView: TextView? = null
    private var mPriorityTextView: TextView? = null


    init {
        mTittleTextView = itemView.findViewById(R.id.film_tittle_textView)
        mYearTextView = itemView.findViewById(R.id.film_year_TextView)
        mPriorityTextView = itemView.findViewById(R.id.film_priority_textView)
    }

    fun bind(film: Film, viewModel: FilmsViewModel){
        mTittleTextView?.text = film.tittle
        mYearTextView?.text = film.year.toString()
        mPriorityTextView?.text = film.priority.toString()
        itemView.film_card_view.setOnClickListener{
            Log.d("FilmsIdPass:", film.filmId.toString())
            val action = FilmsFragmentDirections.actionFilmsFragmentToFilmDetailedFragment(filmid = film.filmId)
            itemView.findNavController().navigate(action)
        }
        itemView.delete_item.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setMessage("Are you sure that you want to delete ${film.tittle}?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, which ->
                   viewModel.deleteFilm(film)
                    Toast.makeText(itemView.context,"Film deleted", Toast.LENGTH_SHORT).show()
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                })
                .show()
        }
    }
}