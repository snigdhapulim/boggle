package com.example.myapplication

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentItemListBinding
import com.example.myapplication.placeholder.PlaceholderContent.PlaceholderItem
import java.util.*
import androidx.activity.viewModels


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */

var workIndexs= mutableListOf<Int>();

class AlphaHolder(
    private val binding: FragmentItemListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind( item:String,index:Int,onClickOf:(list:List<Int>)->Unit) {
        binding.charS.text = item
        binding.charS.setOnClickListener {
            if(workIndexs.contains(index)){
                workIndexs= workIndexs.subList(0,workIndexs.indexOf(index)+1)
            }
            else{
                workIndexs.add(index)
            }
            onClickOf(workIndexs)
        }
    }
}


class MyItemRecyclerViewAdapter(val context:Context,
    private val values: List<String>,
    private val onClickOf:(list:List<Int>)->Unit
) : RecyclerView.Adapter<AlphaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlphaHolder {

        val inflater=LayoutInflater.from(parent.context)
        val binding=FragmentItemListBinding.inflate(inflater, parent, false)
        //val button:Button=v.findViewById<Button>(R.id.charS)
        return AlphaHolder(binding)

    }

    override fun onBindViewHolder(holder: AlphaHolder, position: Int) {
        val item = values[position]
        holder.bind(item,position,onClickOf)
    }

    override fun getItemCount(): Int = values.size
}