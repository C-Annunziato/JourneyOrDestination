package com.example.journeyordestination.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.journeyordestination.Adapters.DestinationAdapter
import com.example.journeyordestination.Api.RetrofitInstance
import com.example.journeyordestination.databinding.FragmentRecyclerViewBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "Fragment"

class RecyclerViewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentRecyclerViewBinding
    private lateinit var destinationAdapter: DestinationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        //inflate the fragment with a recyclerview
        binding = FragmentRecyclerViewBinding.inflate(inflater)

        chooseRVLayout()
        bindApiDataToAdapter()

        return binding.root
    }

    //bind adapter and set layout manager for recyclerview
    private fun chooseRVLayout() {
        recyclerView = binding.recyclerViewDestinations
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DestinationAdapter()

    }

    //parse response from getDirections api request and set the response body to destinationTimeData
    private fun bindApiDataToAdapter() {

        lifecycleScope.launchWhenCreated {
            val response = try {
                Log.d(TAG,"${RetrofitInstance.api.getDirections()} GETDIRECTIONSCALL")
                RetrofitInstance.api.getDirections()

            } catch (e: IOException) {
                Log.e(TAG, "IOException you might not have internet connection")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launchWhenCreated
            }
            val responseBody = response.body()
            Log.d(TAG, "$responseBody RESPONSE BODY" )

            if (response.isSuccessful && responseBody != null) {
                destinationAdapter.list =
               responseBody.routes.map { it.legs.map{it.duration.text} }.flatten()

            } else {
                Log.e(TAG, "Response not successful")
            }
        }

    }


}