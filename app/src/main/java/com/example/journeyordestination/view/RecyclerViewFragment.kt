package com.example.journeyordestination.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.R
import com.example.journeyordestination.databinding.FragmentRecyclerViewBinding
import com.example.journeyordestination.viewmodel.DirectionsViewModel
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView


const val hey = "fragment"

class RecyclerViewFragment : Fragment() {

    private val headerAdapter = HeaderAdapter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentRecyclerViewBinding
    private val viewModel: DirectionsViewModel by activityViewModels()
    private lateinit var destinationAdapter: DestinationAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        destinationAdapter = DestinationAdapter { position ->
            viewModel.remove(position)
        }
        chooseRVLayout()
        observeViewModel()

    }

    private fun chooseRVLayout() {
        recyclerView = binding.recyclerViewDestinations
        recyclerView.layoutManager = LinearLayoutManager(context)
        val concatAdapter = ConcatAdapter(headerAdapter, destinationAdapter)
        recyclerView.adapter = concatAdapter
    }

    private fun observeViewModel() {



        viewModel.destinationApiResponse.observe(viewLifecycleOwner) { duration ->
            Log.i(hey, "From: Fragment -> observeViewModel destinationapiresponse called")
            Log.i(hey, "From: Fragment -> observeViewModel destinationapiresponse called")
            Log.i(hey, "From: Fragment -> observeViewModel destinationapiresponse called")
            Log.i(hey, "From: Fragment -> observeViewModel destinationapiresponse called")
            Log.i(hey, "From: Fragment -> observeViewModel destinationapiresponse called")
            duration?.let {

                destinationAdapter.submitList(duration)
                headerAdapter.updateListSize(duration.size)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            isLoading.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                binding.listError.visibility = View.GONE
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error.let {
                binding.listError.text = viewModel.error.value.toString()
                binding.listError.visibility = View.VISIBLE
            }
        }
    }
}
