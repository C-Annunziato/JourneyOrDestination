package com.example.journeyordestination.view

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.R
import com.example.journeyordestination.viewmodel.DirectionsViewModel
import com.google.android.material.textfield.TextInputLayout
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView
import kotlinx.coroutines.launch

const val TAG2 = "frag"
class HeaderAdapter() : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {

    private var headerNum = 0

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val vm = DirectionsViewModel()
        private val addEntry: ImageView = view.findViewById(R.id.complete_entry)
        private val swapDirections: ImageView = view.findViewById(R.id.swap_directions)
        private var toggle = true


        init {
            swapDirections.setOnClickListener {
                if (toggle) {
                    SwapDirections.swap(view)
                    toggle = !toggle
                } else {
                    SwapDirections.swapBack(view)
                    toggle = !toggle
                }
            }

            addEntry.setOnClickListener {
                    vm.launchApiRequest()
            }

            view.findViewById<PlacesAutocompleteTextView?>(R.id.origin_edit_text)
                ?.setOnPlaceSelectedListener {

                    vm.setOrigin(it.place_id)

                    if(vm.origin.value != null){
//                        Log.i(TAG2, "From : HeaderAdapter -> current origin is not null"
                    }
                }

            view.findViewById<PlacesAutocompleteTextView?>(R.id.destination_edit_text)
                ?.setOnPlaceSelectedListener {


                    vm.setDestination(it.place_id)

                    if(vm.destination.value != null){
//                        Log.i(TAG2, "From : HeaderAdapter -> current dest is not null")
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