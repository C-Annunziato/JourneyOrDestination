package com.example.journeyordestination.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.R


class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {

    private var headerNum = 0

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val rvHeaderTextView: TextView = itemView.findViewById(R.id.header_text)

        fun bind(listSize: Int) {
            rvHeaderTextView.text = "Destinations: $listSize"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(headerNum)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun updateListSize(listSize: Int) {
        headerNum = listSize
        notifyDataSetChanged()
    }

}