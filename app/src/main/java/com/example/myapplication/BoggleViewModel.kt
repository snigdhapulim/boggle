package com.example.myapplication

import androidx.lifecycle.ViewModel

class BoggleViewModel: ViewModel()  {
    var alpha =emptyList<String>();

    fun addAlpha(alphaD:List<String>){
        alpha=alphaD
    }
}