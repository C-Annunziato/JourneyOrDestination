package com.example.journeyordestination.view


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.journeyordestination.R
import com.example.journeyordestination.databinding.FragmentRecyclerViewBinding
import com.example.journeyordestination.databinding.ItemViewBinding
import com.example.journeyordestination.model.Api.ApiResponse.Duration
import com.example.journeyordestination.viewmodel.DirectionsViewModel

const val TAG = "adapter"

class DestinationAdapter(
    private val onClickListener: (int: Int) -> Unit
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

    inner class DestinationViewholder(
        private val binding: ItemViewBinding
    ) : ViewHolder(binding.root) {

        fun createAlertDialog(context: Context?) {
            context?.let {
                androidx.appcompat.app.AlertDialog.Builder(it).setIcon(R.drawable.ic_delete_row)
                    .setMessage("Are you sure you want to delete this Item?")
                    .setPositiveButton("Yes") { _, _ ->
                        onClickListener(bindingAdapterPosition)
                        notifyDataSetChanged()
                    }.setNegativeButton("Cancel") { _, _ ->
                        Toast.makeText(context, "negative", 1000).show()
                    }.create().show()
            }
        }

        fun bind(duration: String) {
            binding.textView.text = duration
            val context = binding.textView.context
            binding.textView.setOnClickListener { createAlertDialog(context) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewholder {
        return DestinationViewholder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_view, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DestinationViewholder, position: Int) {
        Log.i(TAG, "$position")

        holder.bind(getItem(position))
    }

}




