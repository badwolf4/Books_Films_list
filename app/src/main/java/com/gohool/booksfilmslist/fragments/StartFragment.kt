package com.gohool.booksfilmslist.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gohool.booksfilmslist.Comunicator
import com.gohool.booksfilmslist.R
import kotlinx.android.synthetic.main.fragment_start.view.*

/**
 * A simple [Fragment] subclass.
 */
class StartFragment : Fragment() {

    lateinit var comunicator: Comunicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_start, container, false)
        // Inflate the layout for this fragment

        comunicator = activity as Comunicator
        view.booksButton.setOnClickListener{
            comunicator.nextFragment(R.id.booksButton)
        }

        view.filmsButton.setOnClickListener{
            comunicator.nextFragment(R.id.filmsButton)
        }

        return view
    }

    override fun onPause() {
        super.onPause()
    }



}
