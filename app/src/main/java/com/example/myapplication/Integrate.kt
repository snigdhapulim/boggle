package com.example.myapplication

import android.util.Log

class Integrate {
    companion object {
        fun checkAlphaPosition(index: Int, indexList: List<Int>): Boolean {
            if(indexList.size==0){ return true}
            val intLast = indexList.get(indexList.size - 1)
            if (intLast + 1 == index) {
                return true
            } else if (intLast - 1 == index) {
                return true
            } else if (intLast + 4 == index) {
                return true
            } else if (intLast - 4 == index) {
                return true
            } else if (intLast + 3 == index) {
                return true
            } else if (intLast - 3 == index) {
                return true
            } else if (intLast + 5 == index) {
                return true
            } else if (intLast - 5 == index) {
                return true
            }
            return false
        }

        fun isValidLength(indexList: List<Int>): Boolean {
            if(indexList.size>=4){return true}
            return false
        }

        fun word(word:String):Boolean{
            return true
        }
    }
}