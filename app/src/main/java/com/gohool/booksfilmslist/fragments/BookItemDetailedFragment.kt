package com.gohool.booksfilmslist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.classes.Book
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_book_item_detailed.*


class BookItemDetailedFragment : Fragment() {
//    private lateinit var tittle: String
//    fun onBookSet(book : String)
//    {
//        tittle = book
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_book_item_detailed, container, false)

        var tittle = arguments?.getString("tittle")
        var author = arguments?.getString("author")
        var type = arguments?.getString("type")
        var description = arguments?.getString("description")
        var priority = arguments?.getInt("priority")

        val bookTittle = view.findViewById<TextView>(R.id.tittle_detailed)
        val bookAuthor = view.findViewById<TextView>(R.id.author_detailed)
        val bookType = view.findViewById<TextView>(R.id.type_detailed)
        val bookDescription = view.findViewById<TextView>(R.id.description_detailed)
        val bookPriority = view.findViewById<TextView>(R.id.priority_detailed)

        bookTittle.setText(tittle)
        bookAuthor.setText(author)
        bookType.setText(type)
        bookDescription.setText(description)
        bookPriority.setText(priority?.let { it.toString() })
        Toast.makeText(context, tittle, Toast.LENGTH_SHORT).show()
        return view
    }

//    override fun onStart() {
//        super.onStart()
//        bookTittle?.setText(tittle)
//    }



}
