package com.example.journeyordestination.view


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.R
import com.example.journeyordestination.viewmodel.DirectionsViewModel
import com.example.journeyordestination.databinding.FragmentRecyclerViewBinding
import com.example.journeyordestination.viewmodel.Constants
import org.w3c.dom.Text

const val LOG = "fragment"

class RecyclerViewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentRecyclerViewBinding
    private val viewModel: DirectionsViewModel by viewModels()
    private val destinationAdapter = DestinationAdapter(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        //inflate the fragment with a recyclerview
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        chooseRVLayout()
        viewModel.fetchData()
        observeViewModel()
    }


    //bind adapter and set layout manager for recyclerview
    private fun chooseRVLayout() {
        recyclerView = binding.recyclerViewDestinations
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = destinationAdapter
    }


    fun observeViewModel() {
        viewModel.apiResponse.observe(viewLifecycleOwner, Observer { duration ->
            duration?.let {

                destinationAdapter.updateItems(it as MutableList<String>)
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    binding.listError.visibility = View.GONE
                }
            }
        })
    }

    fun createAlertDialog(textView: TextView) {
        val removalItemAlertDialog = context?.let {
            AlertDialog.Builder(it).setIcon(R.drawable.ic_delete_row)
                .setMessage("Are you sure you want to delete this Item?")
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(context, "positive", 1000).show()
                }.setNegativeButton("Cancel") { _, _ ->
                    Toast.makeText(context, "negative", 1000).show()
                }.create()
        }
        textView.setOnClickListener {
            destinationAdapter.removeItems(DestinationAdapter.DestinationViewholder(it)) }
    }



}


