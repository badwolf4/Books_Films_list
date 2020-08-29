package com.gohool.booksfilmslist.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.gohool.booksfilmslist.R
import kotlinx.android.synthetic.main.fragment_add_film.view.*
import org.w3c.dom.Text


class FilmEditFragment : Fragment() {

    lateinit var priority : SeekBar
    lateinit var priorityValue : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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

        Log.v("MyLog","year set")
//        arguments?.getInt("priority").let {
//            if (it != null) {
//                view.priority_value.setText(it).toString()
//            }
//        }

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

        view.add_button.setOnClickListener {
            Toast.makeText(context, "Film added", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_item_opion_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //saving updated item
        return super.onOptionsItemSelected(item)
    }


}