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
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.network.DictionaryAPIService
import kotlinx.coroutines.launch


class Alphabets : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var submit: Button
    private lateinit var clear: Button
    private lateinit var charGrids: MyItemRecyclerViewAdapter
    private lateinit var boggleViewModel: BoggleViewModel
    private lateinit var card: CardView
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
        card=view.findViewById<CardView>(R.id.cardV)
        if (alpha != null){
            recyclerView= view.findViewById(R.id.list)
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
            },{->submit.isEnabled=true},{ -> submit.isEnabled = false }, {->clear.isEnabled=true},
                {wordIndex:List<Int>->
                    boggleViewModel.updateWordIndex(wordIndex)
                    updateText(view,alpha)
                })
            recyclerView.layoutManager = GridLayoutManager(activity, 4)
            recyclerView.hasOnClickListeners()
            recyclerView.adapter=charGrids
        }

        clear.setOnClickListener {
            restartFragment()
            if (alpha != null) {updateText(view,alpha)}
        }
        submit.setOnClickListener {
            var finalWord = view.findViewById<TextView>(R.id.word).text.toString()
            boggleViewModel.viewModelScope.launch {
                try {
                    val listResult = DictionaryAPIService.DictionaryAPI.retrofitService.getWord(finalWord)
                    Integrate.word(finalWord)
                }
                catch (e:Exception){
                    Integrate.finalScore-=10
                }
            }
            restartFragment()
            if (alpha != null) {updateText(view,alpha)}
            callingScore()
        }

        return view
    }

    fun restartFragment() {
        boggleViewModel.emptyList()
        submit.isEnabled=false
        clear.isEnabled=false
        charGrids.resetList()
    }

    fun generate(al:List<String>){
        charGrids.update(ArrayList(al))
        restartFragment()
    }

    fun callingScore(){
        var activity:MainActivity=getActivity() as MainActivity
        activity.getScore()
    }

    fun updateText(view:View,alpha:List<String>){
        val iterate=boggleViewModel.wordIndex.iterator()
        var str=String()
        while (iterate.hasNext()) {
            str=str+alpha.get(iterate.next())
        }
        view.findViewById<TextView>(R.id.word).text=str.toString()

    }
}