package com.example.journeyordestination.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.R


class DestinationAdapter() :
    androidx.recyclerview.widget.ListAdapter<String, DestinationAdapter.DestinationViewholder>(
        DestinationItemCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewholder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        return DestinationViewholder(layout)
    }

    override fun onBindViewHolder(holder: DestinationViewholder, position: Int) {
        holder.textview.text = getItem(position)
    }

    class DestinationViewholder(view: View) : RecyclerView.ViewHolder(view) {
        val textview: TextView = view.findViewById(R.id.text_view)
    }

//    private fun createOnClickListener(duration: String): View.OnClickListener{
//        return View.OnClickListener {
//                 RecyclerViewFragment
//        }
//    }

}

class DestinationItemCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
