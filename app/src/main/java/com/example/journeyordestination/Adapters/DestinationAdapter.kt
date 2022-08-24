package com.example.journeyordestination.Adapters




import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.R

class DestinationAdapter : RecyclerView.Adapter<DestinationAdapter.DestinationViewholder>() {

var list = listOf<String>()

    class DestinationViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textview: TextView = itemView.findViewById(R.id.text_view)

    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewholder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return DestinationViewholder(layout)
    }

    override fun onBindViewHolder(holder: DestinationViewholder, position: Int) {
        holder.textview.text = list[position].toString()
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
