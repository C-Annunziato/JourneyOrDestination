package com.example.journeyordestination.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.Adapters.DestinationAdapter
import com.example.journeyordestination.R
import com.example.journeyordestination.databinding.FragmentRecyclerViewBinding


class RecyclerViewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentRecyclerViewBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = binding.recyclerViewDestinations
        chooseLayout()
    }

    private fun chooseLayout() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DestinationAdapter()

    }

}