package com.example.journeyordestination.view


import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.journeyordestination.R
import com.example.journeyordestination.databinding.ItemViewBinding
import com.google.android.material.textfield.TextInputEditText


const val TAG = "adapter"

class DestinationAdapter(
    private val removeItem: (int: Int) -> Unit
) : androidx.recyclerview.widget.ListAdapter<String, DestinationAdapter.DestinationViewholder>(
    DiffUtilCallback
) {
    private lateinit var editText: TextInputEditText


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


        val context = binding.textView.context

        init {
            binding.textView.setOnClickListener { createAlertDialog(context) }
            binding.textViewRv.setOnClickListener { createEditTextDialog(context) }

        }

        fun createAlertDialog(context: Context?) {
            context?.let {
                AlertDialog.Builder(it).setIcon(R.drawable.ic_delete_row)
                    .setMessage("Are you sure you want to delete this Item?")
                    .setPositiveButton("Yes") { _, _ ->
                        removeItem(bindingAdapterPosition)
                        notifyItemRemoved(bindingAdapterPosition)
                    }.setNegativeButton("Cancel") { _, _ ->
                    }.create().show()
            }
        }

        fun createEditTextDialog(context: Context?) {
            context?.let {
                AlertDialog.Builder(it).create().apply {
                    val customLayout: View = LayoutInflater.from(context).inflate(
                            R.layout.set_destination_name_dialog, null
                        )
                    setView(customLayout)
                    val editText: TextInputEditText =
                        (customLayout).findViewById(R.id.destination_text_input_edit_text)
                    editText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
                        binding.textViewRv.text = editText.text.toString()
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
//        val editText: TextInputEditText = parent.findViewById(R.id.destination_text_input_edit_text)
//        val textViewRv: TextView = parent.findViewById(R.id.text_view_rv)
//        textViewRv.text = editText.text.toString()


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




