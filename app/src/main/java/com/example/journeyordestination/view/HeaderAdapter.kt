package com.example.journeyordestination.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.R
import com.google.android.material.textfield.TextInputLayout


class HeaderAdapter() : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {

    private var headerNum = 0

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val swapDirections: ImageView = view.findViewById(R.id.swap_directions)
        private var toggle = true
        val editText1: TextInputLayout = view.findViewById(R.id.first_text_field_layout)
        val editText2: TextInputLayout = view.findViewById(R.id.second_text_field_layout)

        init {
            swapDirections.setOnClickListener {
                if (toggle) {
                    SwapDirections.swap(view)
                    editText1.slideUp(1000L, 0L)
                    editText2.slideDown(1000L, 0L)
                    toggle = !toggle
                } else {
                    SwapDirections.swapBack(view)
                    editText2.slideUp(1000L, 0L)
                    editText1.slideDown(1000L, 0L)
                    toggle = !toggle
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }

    fun updateListSize(listSize: Int) {
        headerNum = listSize
        notifyDataSetChanged()
    }


}