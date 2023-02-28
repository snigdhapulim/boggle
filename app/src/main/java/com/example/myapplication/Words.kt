package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class Words : Fragment() {
    private lateinit var newGame: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_words, container, false)
        newGame= view.findViewById(R.id.new_game)

        newGame.setOnClickListener {
            var activity:MainActivity=getActivity() as MainActivity
            activity.generate()
        }

        return view
    }


}