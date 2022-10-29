package com.example.journeyordestination.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.example.journeyordestination.model.Api.ApiResponseDirections.MapDataResponse
import com.example.journeyordestination.model.Api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

const val TAG = "fragment"

class DirectionsViewModel() : ViewModel() {

    private val _destinationaApiResponse = MutableLiveData<List<String>>()

    private val _loading = MutableLiveData<Boolean>(false)
    private val _error = MutableLiveData<String>()

    private val _origin = MutableLiveData<String?>()
    private val _destination = MutableLiveData<String?>()

    val destinationApiResponse: LiveData<List<String>> = _destinationaApiResponse

    val loading: LiveData<Boolean> = _loading
    val error: LiveData<String> = _error

    var origin: LiveData<String?> = _origin
    val destination: LiveData<String?> = _destination

    fun setOrigin(string: String) {
        _origin.value = string
    }

    fun setDestination(string: String) {
        _destination.value = string
    }

    fun launchApiRequest() {
        fetchDirectionsData()
    }

    private fun fetchDirectionsData() {
        val apiKey = com.example.journeyordestination.BuildConfig.DIRECTIONS_API_KEY

        _loading.value = true

        RetrofitInstance.call(
            origin.value, destination.value, apiKey
        ).enqueue(object : Callback<MapDataResponse> {
            override fun onResponse(

                call: Call<MapDataResponse>, response: Response<MapDataResponse>
            ) {
                val body = response.body()?.routes?.map {
                    it.legs.map { it.duration.text }
                }
                Log.i(
                    TAG,
                    "From ViewModel -> value of body is ${body}"
                )
                if (body != null) {
                    _destinationaApiResponse.value = if(_destinationaApiResponse.value == null) {
                    body.flatten()
                    } else body.flatten().plus(_destinationaApiResponse.value as List<String>)

                    Log.i(
                        TAG,
                        "From ViewModel -> value of api response is ${destinationApiResponse.value}"
                    )
                }
                _error.value = null
                _loading.value = false
            }

            override fun onFailure(call: Call<MapDataResponse>, t: Throwable) {
                onError(t.localizedMessage)
            }
        })
    }

    private fun onError(message: String?) {
        if (error.value != null) {
            _error.value = message
            _loading.value = false
        }
    }

    fun remove(int: Int) {
        Log.i(TAG, "From ViewModel -> this is the int value $int")
        if (!_destinationaApiResponse.value.isNullOrEmpty()) {
            destinationApiResponse.value?.toMutableList()?.apply {
                removeAt(int)
                _destinationaApiResponse.value = this
            }
        }
    }
}
