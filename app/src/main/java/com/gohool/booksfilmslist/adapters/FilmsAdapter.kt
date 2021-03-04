package com.gohool.booksfilmslist.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gohool.booksfilmslist.ui.MainActivity
import com.gohool.booksfilmslist.data.films.Film
import com.gohool.booksfilmslist.viewHolders.FilmViewHolder
import com.gohool.booksfilmslist.viewModels.FilmsViewModel
import kotlinx.android.synthetic.main.book_item.view.*

class FilmsAdapter(private  val films : List<Film>, private val viewModel : FilmsViewModel) : RecyclerView.Adapter<FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FilmViewHolder(
            inflater,
            parent
        )
    }

    override fun getItemCount() = films.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(films.get(position), viewModel)

        val tittle = films.get(position).tittle

//        holder.itemView.delete_item.setOnClickListener{
//            AlertDialog.Builder(holder.itemView.context)
//                .setMessage("Are you sure that you want to delete ${tittle}?")
//                .setPositiveButton("Yes", { dialog, which ->
////                    TODO uncomment
////                   MainActivity.viewModel.deleteFilm(films.get(position))
//
//                    Toast.makeText(holder.itemView.context,"Film deleted", Toast.LENGTH_SHORT).show()
//
//                })
//                .setNegativeButton("No", { dialog, which -> })
//                .show()
//        }
    }

}
