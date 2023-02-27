package com.example.myapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentItemListBinding
import com.example.myapplication.placeholder.PlaceholderContent.PlaceholderItem


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */

class AlphaHolder(
    private val binding: FragmentItemListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item:String, index:Int,wordsIndex: List<Int>, onClickOf:(list:List<Int>)->Unit, enabled: () -> Unit,
        enabledFalse: () -> Unit,updateWordIndex:(wordIndex:List<Int>)->Unit,addingWordIndex:(index:Int)->Unit
    ) {
        binding.charS.text = item
        binding.charS.setOnTouchListener { view, motionEvent ->
            Log.i("check",wordsIndex.toString())
            Log.i("check",index.toString())
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    if(wordsIndex.contains(index)) {
                        updateWordIndex(wordsIndex.subList(0, wordsIndex.indexOf(index) + 1).toList())
                    }
                    else if(Integrate.checkAlphaPosition(index, wordsIndex.toList())){
                        addingWordIndex(index)
                    }
                    onClickOf(wordsIndex.toList())
                    if(Integrate.isValidLength(wordsIndex.toList())){enabled()}
                    else{enabledFalse()
                    }
                }
            }
            return@setOnTouchListener view.onTouchEvent(motionEvent) ?: true
        }
    }

}


class MyItemRecyclerViewAdapter(
    val context: Context,
    private val values: List<String>,
    private val wordsIndex: List<Int>,
    private val onClickOf: (list: List<Int>) -> Unit,
    private val enabled: () -> Unit,
    private val enabledFalse: () -> Unit,
    private val updateWordIndex:(wordIndex:List<Int>)->Unit,
    private val addingWordIndex:(index:Int)->Unit
) : RecyclerView.Adapter<AlphaHolder>() {
    private lateinit var boggleViewModel: BoggleViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlphaHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=FragmentItemListBinding.inflate(inflater, parent, false)
        //val button:Button=v.findViewById<Button>(R.id.charS)
        //boggleViewModel= ViewModelProvider(this).get(BoggleViewModel::class.java)
        return AlphaHolder(binding)

    }

    override fun onBindViewHolder(holder: AlphaHolder, position: Int) {
        val item = values[position]
        holder.bind(item,position,wordsIndex,onClickOf,enabled,enabledFalse,updateWordIndex,addingWordIndex)
    }

    override fun getItemCount(): Int = values.size


}