package com.gohool.booksfilmslist.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.gohool.booksfilmslist.R
import kotlinx.android.synthetic.main.fragment_start.view.*

/**
 * A simple [Fragment] subclass.
 */
class StartFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_start, container, false)
        // Inflate the layout for this fragment

        view.booksButton.setOnClickListener{
            //comunicator.nextFragment(Constants.BOOKS_FRAGMENT)
            val action = StartFragmentDirections.actionStartFragmentToBooksFragmet()
            view.findNavController().navigate(action)
        }

        view.filmsButton.setOnClickListener{
            //comunicator.nextFragment(Constants.FILMS_FRAGMENT)
            val action = StartFragmentDirections.actionStartFragmentToFilmsFragment2()
            view.findNavController().navigate(action)
        }

        return view
    }

    override fun onPause() {
        super.onPause()
    }



}
