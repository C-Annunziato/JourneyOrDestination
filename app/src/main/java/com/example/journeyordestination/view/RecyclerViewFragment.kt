package com.example.journeyordestination.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.databinding.FragmentRecyclerViewBinding
import com.example.journeyordestination.viewmodel.DirectionsViewModel

const val hey = "fragment"

class RecyclerViewFragment : Fragment() {

    private val headerAdapter = HeaderAdapter()
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentRecyclerViewBinding
    private val viewModel: DirectionsViewModel by viewModels()
    private lateinit var destinationAdapter: DestinationAdapter


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

    fun observeViewModel() {
        viewModel.apiResponse.observe(viewLifecycleOwner, Observer { duration ->
            duration?.let {
                Log.i(hey, "$duration")
                destinationAdapter.submitList(duration)
                headerAdapter.updateListSize(duration.size)
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

}


