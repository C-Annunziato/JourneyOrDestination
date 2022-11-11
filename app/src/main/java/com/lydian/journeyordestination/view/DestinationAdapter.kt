package com.lydian.journeyordestination.view


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lydian.journeyordestination.R
import com.lydian.journeyordestination.databinding.ItemViewBinding
import com.google.android.material.textfield.TextInputEditText


const val TAG = "adapter"

class DestinationAdapter(
    private val removeItem: (int: Int) -> Unit
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


        private val context: Context = binding.textView.context

        init {
            binding.textView.setOnClickListener { createAlertDialog(context) }
            binding.textViewRv.setOnClickListener { createEditTextDialog(context) }
        }

        //alert dialog for deleting rv entries
        private fun createAlertDialog(context: Context?) {
            context?.let {
                AlertDialog.Builder(it)
                    .setMessage("Are you sure you want to delete this Item?")
                    .setPositiveButton("Yes") { _, _ ->
                        removeItem(bindingAdapterPosition)
                    }.setNegativeButton("Cancel") { _, _ ->
                    }.create().show()
            }
        }

        //alert dialog for changing name of entry
        private fun createEditTextDialog(context: Context?) {
            context?.let {
                AlertDialog.Builder(it).create().apply {
                    val customLayout: View = LayoutInflater.from(context).inflate(
                        R.layout.set_destination_name_dialog, null
                    )
                    setView(customLayout)
                    val editText: TextInputEditText =
                        (customLayout).findViewById(R.id.destination_text_input_edit_text)
                    editText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
                        if (editText.text.toString().length < 16) {
                            binding.textViewRv.text = editText.text.toString()
                        } else {
                            Toast.makeText(context, "Enter less than 15 characters total", Toast.LENGTH_LONG)
                                .show()
                            createEditTextDialog(context)
                        }
                        this.dismiss()
                        return@OnEditorActionListener true
                    })
                }.show()
            }
        }

        fun bind(duration: String) {
            binding.textView.text = duration

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
        holder.bind(getItem(position))

    }
}




