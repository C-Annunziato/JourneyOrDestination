package com.example.journeyordestination.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.example.journeyordestination.model.Api.ApiResponseDirections.MapDataResponse
import com.example.journeyordestination.model.Api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.typeOf

const val TAG = "vm"

class DirectionsViewModel() : ViewModel() {

    private val _destinationApiResponse = MutableLiveData<List<String>>(listOf())
    private val _loading = MutableLiveData(false)
    private val _howTo = MutableLiveData(true)
    private val _error = MutableLiveData<String>()
    private val _origin = MutableLiveData<String?>()
    private val _destination = MutableLiveData<String?>()

    val destinationApiResponse: LiveData<List<String>> = _destinationApiResponse
    val loading: LiveData<Boolean> = _loading
    val howTo: LiveData<Boolean> = _howTo
    val error: LiveData<String> = _error
    var origin: LiveData<String?> = _origin
    val destination: LiveData<String?> = _destination

    fun setOrigin(string: String) {
        _origin.value = string
    }

    fun setDestination(string: String) {
        _destination.value = string
    }

    //function called in HeaderAdapter in addEntry.setOnClickListener
    fun launchApiRequest() {
        fetchDirectionsData()
    }

    private fun fetchDirectionsData() {
        val apiKey = com.example.journeyordestination.BuildConfig.DIRECTIONS_API_KEY
        //set progress bar true
        _loading.value = true
        // pass in place_id's and api key
        RetrofitInstance.call(
            origin.value, destination.value, apiKey
        ).enqueue(object : Callback<MapDataResponse> {
            override fun onResponse(

                call: Call<MapDataResponse>, response: Response<MapDataResponse>
            ) {
                //get duration data
                val body = response.body()?.routes?.map {
                    it.legs.map { it.duration.text }
                }
                if (body != null) {
                    //force update livedata and add the original list to the response
                    //this is necessary else the list will only consist of your new data i.e. 1 entry
                    //all previous entries will disappear; a quirk of LiveData
                    _destinationApiResponse.value =
                        body.flatten().plus(_destinationApiResponse.value as List<String>)
                }
                // progress bar false, and error textview false
                _error.value = null
                _loading.value = false
            }

            override fun onFailure(call: Call<MapDataResponse>, t: Throwable) {
                onError(t.localizedMessage)
            }
        })
    }

    private fun onError(message: String?) {
        // set progress bar and error textview values appropriately if error
        if (error.value != null) {
            _error.value = message
            _loading.value = false
        }
    }

    fun remove(int: Int) {
        //if list is not empty then removal can happen and force update livedata
        if (!_destinationApiResponse.value.isNullOrEmpty()) {
            destinationApiResponse.value?.toMutableList()?.apply {
                removeAt(int)
                _destinationApiResponse.value = this
            }
        }
    }
}
