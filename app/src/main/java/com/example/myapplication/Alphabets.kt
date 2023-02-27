package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Alphabets : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var charGrids: MyItemRecyclerViewAdapter
    val activity: Activity = Activity()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //gridView= getView()?.findViewById(R.id.grid) ?:
        val view=inflater.inflate(R.layout.fragment_alphabets, container, false )
        val bundle = arguments
        val alpha = bundle?.getStringArrayList("alpha")
        if (alpha != null){
            recyclerView= view.findViewById(R.id.list)
            val card=view.findViewById<CardView>(R.id.cardV)
            card.visibility=View.VISIBLE
            charGrids=  MyItemRecyclerViewAdapter(activity, alpha.toList()){ selected ->
                for (frag in 0..alpha.size){
                    if(selected.contains(frag)){

                    }
                    else{

                    }
                }
            }
            recyclerView.layoutManager = GridLayoutManager(activity, 4)
            recyclerView.hasOnClickListeners()
            recyclerView.adapter=charGrids
        }
        return view
    }

}