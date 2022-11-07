package com.example.journeyordestination.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.R
import com.example.journeyordestination.viewmodel.DirectionsViewModel
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView

const val TAG2 = "frag"

class HeaderAdapter(val vm: DirectionsViewModel) :
    RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {

    private var headerNum = 0

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val addEntry: ImageView = view.findViewById(R.id.complete_entry)
        private val destinationEditText: PlacesAutocompleteTextView =
            view.findViewById(R.id.destination_edit_text)
        private val originExitText: PlacesAutocompleteTextView =
            view.findViewById(R.id.origin_edit_text)
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
                destinationEditText.apply {
                    clearFocus()
                    text.clear()
                }
                originExitText.apply {
                    clearFocus()
                    text.clear()
                }
            }

            view.findViewById<PlacesAutocompleteTextView?>(R.id.origin_edit_text)
                ?.setOnPlaceSelectedListener {
                    vm.setOrigin(it.place_id)
                }

            view.findViewById<PlacesAutocompleteTextView?>(R.id.destination_edit_text)
                ?.setOnPlaceSelectedListener {
                    vm.setDestination(it.place_id)
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