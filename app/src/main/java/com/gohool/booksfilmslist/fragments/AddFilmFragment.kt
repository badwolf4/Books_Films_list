package com.gohool.booksfilmslist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.gohool.booksfilmslist.R
import kotlinx.android.synthetic.main.fragment_add_film.view.*
import org.w3c.dom.Text


class AddFilmFragment : Fragment() {

    lateinit var priority : SeekBar
    lateinit var priorityValue : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_add_film, container, false)

        priority = view.findViewById(R.id.priority) as SeekBar
        priorityValue = view.findViewById(R.id.priority_value) as TextView

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


}