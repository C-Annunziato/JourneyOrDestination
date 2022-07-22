package com.example.journeyordestination.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.R
import java.util.zip.Inflater

class DestinationAdapter : RecyclerView.Adapter<DestinationAdapter.DestinationViewholder>() {



    class DestinationViewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val textview: TextView = itemview.findViewById(R.id.text_view)

    }

    override fun getItemCount(): Int {
        return charList().size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewholder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return DestinationViewholder(layout)
    }

    override fun onBindViewHolder(holder: DestinationViewholder, position: Int) {
        holder.textview.text = charList()[position].toString()
    }




}

fun charList(): MutableList<Char> {
    val chars = 'A'..'Z'
    val charList: MutableList<Char> = mutableListOf()
    charList.addAll(chars)
    return charList
}