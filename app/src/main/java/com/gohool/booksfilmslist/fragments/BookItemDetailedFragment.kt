package com.gohool.booksfilmslist.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.BaseColumns
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import com.gohool.booksfilmslist.Comunicator

import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.adapters.BookDataBaseHelper
import com.gohool.booksfilmslist.adapters.TableInfo
import com.gohool.booksfilmslist.classes.Book
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_book_item_detailed.*


class BookItemDetailedFragment : Fragment() {

    lateinit var comunicator : Comunicator
    var bundle : Bundle? = Bundle()
    var tittle : String? = null
    var author : String? = null
    var type : String? = null
    var description : String? = null
    var priority : Int? = null
    var bookId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_book_item_detailed, container, false)

        comunicator = activity as Comunicator



        tittle = arguments?.getString("tittle")
        author = arguments?.getString("author")
        type = arguments?.getString("type")
        description = arguments?.getString("description")
        priority = arguments?.getInt("priority")
        bookId = arguments?.getInt("bookId")


        val bookTittle = view.findViewById<TextView>(R.id.tittle_detailed)
        val bookAuthor = view.findViewById<TextView>(R.id.author_detailed)
        val bookType = view.findViewById<TextView>(R.id.type_detailed)
        val bookDescription = view.findViewById<TextView>(R.id.description_detailed)
        val bookPriority = view.findViewById<TextView>(R.id.priority_detailed)

        bookTittle.setText(tittle)
        bookAuthor.setText(author)
        bookType.setText(type)
        bookDescription.setText(description)
        bookPriority.setText(priority?.toString())
        //Toast.makeText(context, tittle, Toast.LENGTH_SHORT).show()
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.book_detailed_option_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val bundle = Bundle()
        val id = item.itemId
        when(id){
            R.id.edit_button-> {
                bundle.putString("tittle", tittle)
                bundle.putString("author", author)
                bundle.putString("type", type)
                bundle.putString("description", description)
                bookId?.let { bundle.putInt("bookId", it) }
                priority?.let { bundle.putInt("priority", it) }

                comunicator.nextEditFragment(bundle)
                //Toast.makeText(activity, "Edit pressed", Toast.LENGTH_SHORT).show()
            }
            R.id.delete_button->
            {
                val dbHelper = view?.context?.let { BookDataBaseHelper(it) }
                val db = dbHelper?.writableDatabase
                db?.delete(TableInfo.TABLE_NAME,BaseColumns._ID+"=?", arrayOf(bookId.toString()))
                bookId?.let { comunicator.nextDeletedBook(it) }
                db?.close()
                //Toast.makeText(activity, "Delete pressed", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
