package com.example.journeyordestination.view




import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.R
import com.example.journeyordestination.model.Api.ApiResponse.Duration
import com.example.journeyordestination.model.Api.ApiResponse.Leg
import com.example.journeyordestination.viewmodel.Constants
import org.w3c.dom.Text


class DestinationAdapter (var durationList: MutableList<String>) :
    RecyclerView.Adapter<DestinationAdapter.DestinationViewholder>() {

    fun updateItems(newItems: MutableList<String>) {
        durationList.clear()
        durationList.addAll(newItems)
        durationList.add(newItems.toString())
        notifyDataSetChanged()
    }

    fun removeItems(holder: DestinationViewholder){
        holder.textview.setOnClickListener {
          durationList.removeAt(holder.adapterPosition)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        Log.d(Constants.TAG,"$durationList GetItemCount")
        return durationList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewholder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return DestinationViewholder(layout)
    }

    override fun onBindViewHolder(holder: DestinationViewholder, position: Int) {
        holder.textview.text = durationList[position]
        removeItems(holder)
    }

    class DestinationViewholder(view: View) : RecyclerView.ViewHolder(view) {
        val textview: TextView = view.findViewById(R.id.text_view)

    }




//    private val diffCallback = object : DiffUtil.ItemCallback<MapData>() {
//
//
//
//        override fun areItemsTheSame(oldItem: MapData, newItem: MapData): Boolean {
//            return oldItem.duration == newItem.duration
//        }
//
//        override fun areContentsTheSame(oldItem: MapData, newItem: MapData): Boolean {
//            return oldItem == newItem}
//
//    }
//
//    private val differ = AsyncListDiffer(this, diffCallback)
//
//    var destinationTimeData: List<MapData>
//        get() = differ.currentList
//        set(value) {
//            differ.submitList(value)
//        }


}
