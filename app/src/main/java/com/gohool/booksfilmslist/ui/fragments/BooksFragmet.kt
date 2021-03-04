package com.gohool.booksfilmslist.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gohool.booksfilmslist.adapters.BooksAdapter
import com.gohool.booksfilmslist.R
import com.gohool.booksfilmslist.data.books.Book
import com.gohool.booksfilmslist.data.books.BookDatabase
import com.gohool.booksfilmslist.data.books.BookRepository
import com.gohool.booksfilmslist.data.films.FilmsDatabase
import com.gohool.booksfilmslist.data.films.FilmsRepository
import com.gohool.booksfilmslist.viewModels.BookViewModel
import com.gohool.booksfilmslist.viewModels.BooksViewModelFactory
import com.gohool.booksfilmslist.viewModels.FilmsViewModel
import com.gohool.booksfilmslist.viewModels.FilmsViewModelFactory
import kotlinx.android.synthetic.main.books_fragmet.*
import kotlinx.android.synthetic.main.books_fragmet.view.*


class BooksFragmet : Fragment() {


    lateinit var bookList : LiveData<List<Book>>

    val database by lazy { BookDatabase.getInstance(this.requireActivity().applicationContext) }
    val repository by lazy { BookRepository(database.booksDao()) }

    private val viewModel: BookViewModel by activityViewModels {
        BooksViewModelFactory(repository)
    }



    companion object{
        lateinit var adapter : BooksAdapter
        fun newInstance(): BooksFragmet =
            BooksFragmet()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view: View = inflater.inflate(R.layout.books_fragmet, container, false)

        view.floating_add_btn.setOnClickListener{
            val action = BooksFragmetDirections.actionBooksFragmetToBookEditFragment(-1)
            findNavController().navigate(action)
        }

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookList = viewModel.allBooks
        bookList.observe(viewLifecycleOwner, {
            if(it!=null && !it.isEmpty()){
                recycler_view_books.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = BooksAdapter( it, viewModel)
                }
            }
        })

    }

    override fun onResume() {
        super.onResume()
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
//                var filteredBooks = ArrayList<Book>()
//
//                if(!newText.isNullOrEmpty()){
//                    val text = newText.toLowerCase()
//                    var book : Book
//
//                    for(i in 0 until bookList.size){
//                        book = bookList.get(i)
//                        if( book.title.toLowerCase().contains(text)
//                            or book.author.toLowerCase().contains(text)
//                            or book.type.toLowerCase().contains(text)){
//
//                            filteredBooks.add(bookList.get(i))
//                        }
//                    }
//                }
//                else{
//                    filteredBooks=bookList
//                }
//                viewBooks(filteredBooks)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.tittle-> {
                //Toast.makeText(context, "Tittle pressed", Toast.LENGTH_SHORT).show()
//                bookList.sortWith(compareBy {it.title})
//                viewBooks(bookList)
            }
            R.id.author-> {
                //Toast.makeText(context, "Author pressed", Toast.LENGTH_SHORT).show()
//                bookList.sortWith(compareBy {it.author})
//                viewBooks(bookList)
            }
            R.id.priority-> {
                //Toast.makeText(context, "Priority pressed", Toast.LENGTH_SHORT).show()
//                bookList.sortWith(compareBy({it.priority}, {it.title}))
//                viewBooks(bookList)
            }
        }

        return super.onOptionsItemSelected(item)
    }

}












