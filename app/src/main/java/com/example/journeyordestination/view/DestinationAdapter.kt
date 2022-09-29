package com.example.journeyordestination.view


import android.app.AlertDialog
import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.journeyordestination.R
import com.example.journeyordestination.databinding.FragmentRecyclerViewBinding
import com.example.journeyordestination.databinding.ItemViewBinding
import com.example.journeyordestination.model.Api.ApiResponse.Duration
import com.example.journeyordestination.viewmodel.DirectionsViewModel

const val TAG = "adapter"

class DestinationAdapter(
    private val onClickListener: OnClickListener
) : androidx.recyclerview.widget.ListAdapter<String, DestinationAdapter.DestinationViewholder>(
    DiffUtilCallback
) {

    object DiffUtilCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    class DestinationViewholder(
        private val binding: ItemViewBinding,
        onClickListener: OnClickListener
    ) : ViewHolder(binding.root) {
        val textView: TextView = binding.textView

        init {
            binding.textView.setOnClickListener { onClickListener.onClick() }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewholder {
        return DestinationViewholder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_view, parent, false
            ), onClickListener
        )
    }

    override fun onBindViewHolder(holder: DestinationViewholder, position: Int) {
        holder.textView.text = getItem(position)
    }

    class OnClickListener(val clickListener: () -> Unit) {
        fun onClick() = clickListener()
    }

}




