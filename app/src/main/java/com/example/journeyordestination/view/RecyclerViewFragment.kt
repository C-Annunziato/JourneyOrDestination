package com.example.journeyordestination.view


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.viewmodel.DirectionsViewModel
import com.example.journeyordestination.databinding.FragmentRecyclerViewBinding
import com.example.journeyordestination.viewmodel.Constants


class RecyclerViewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentRecyclerViewBinding
    private val viewModel: DirectionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        //inflate the fragment with a recyclerview
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        chooseRVLayout()
    }

    //bind adapter and set layout manager for recyclerview
    private fun chooseRVLayout() {
        recyclerView = binding.recyclerViewDestinations
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DestinationAdapter(viewModel.bindApiDataToAdapter())
    }

    override fun onStart() {
        super.onStart()
        Log.d(Constants.TAG, "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(Constants.TAG,"onPausse")

    }

    override fun onResume() {
        super.onResume()
        Log.d(Constants.TAG, "onResume")
    }



}