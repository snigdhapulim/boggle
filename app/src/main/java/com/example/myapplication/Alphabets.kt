package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Alphabets : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var submit: Button
    private lateinit var clear: Button
    private lateinit var charGrids: MyItemRecyclerViewAdapter
    private lateinit var boggleViewModel: BoggleViewModel
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
        var wordIndex=bundle?.getIntegerArrayList("wordIndex")
        if (wordIndex == null) {wordIndex= ArrayList<Int>() }
        boggleViewModel= ViewModelProvider(this).get(BoggleViewModel::class.java)
        submit= view.findViewById(R.id.submit)
        clear=view.findViewById(R.id.clear)
        if (alpha != null){
            recyclerView= view.findViewById(R.id.list)
            val card=view.findViewById<CardView>(R.id.cardV)
            card.visibility=View.VISIBLE
            charGrids=  MyItemRecyclerViewAdapter(activity, alpha.toList(), { selected ->
                val handler = Handler()
                handler.postDelayed(Runnable {
                    for (frag in 0..alpha.size) {
                        val list_view = recyclerView.getLayoutManager()?.findViewByPosition(frag)
                        var button: Button? = list_view?.findViewById(R.id.charS) as Button?
                        //set the color to black
                        if (selected.contains(frag)) {
                            if (button != null) {
                                button.elevation = 100F
                            };
                        } else {
                            if (button != null) {
                                button.elevation = 5F
                            };
                        }
                    }
                }, 200);
            },{->submit.isEnabled=true},{ -> submit.isEnabled = false },
                {wordIndex:List<Int>->
                    boggleViewModel.updateWordIndex(wordIndex)
                    updateText(view,alpha)})
            recyclerView.layoutManager = GridLayoutManager(activity, 4)
            recyclerView.hasOnClickListeners()
            recyclerView.adapter=charGrids
        }

        clear.setOnClickListener {
            boggleViewModel.emptyList()
            view.findViewById<TextView>(R.id.word).text=null
            //recyclerView.
        }
        submit.setOnClickListener {
            Integrate.word(view.findViewById<TextView>(R.id.word).text.toString())
        }

        return view
    }
    fun updateText(view:View,alpha:List<String>){
        val iterate=boggleViewModel.wordIndex.iterator()
        var str=String()
        while (iterate.hasNext()) {
            str=str+alpha.get(iterate.next())
        }
        Log.i("a",boggleViewModel.wordIndex.toString())
        view.findViewById<TextView>(R.id.word).text=str.toString()
    }
}