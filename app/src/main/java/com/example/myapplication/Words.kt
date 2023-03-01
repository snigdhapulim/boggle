package com.example.myapplication

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.ui.graphics.Color


class Words : Fragment() {
    private lateinit var newGame: Button
    private lateinit var score: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_words, container, false)
        newGame= view.findViewById(R.id.new_game)

        newGame.setOnClickListener {
            var activity:MainActivity=getActivity() as MainActivity
            activity.re_generate()
            Integrate.resetScore()
            update_scro()
        }
        return view
    }
    fun update_scro(){
        Log.i("at Score",Integrate.finalScore.toString())
        //score=view.findViewById<TextView?>(R.id.score)
        //            if(Integer.parseInt(score.text.toString())<Integrate.finalScore){
        //                score.text=Integrate.finalScore.toString()
        //                score.setTextColor(R.color.red)
        //            }
        //            else if(Integer.parseInt(score.text.toString())=Integrate.finalScore){
        //                score.text=Integrate.finalScore.toString()
        //                score.setTextColor(R.color.black)
        //            }
        //            else{
        //                score.text=Integrate.finalScore.toString()
        //                score.setTextColor(R.color.green_l)
        //            }
    }
}