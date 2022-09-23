package com.example.journeyordestination.viewmodel

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeyordestination.R
import com.example.journeyordestination.model.Api.ApiResponse.MapDataResponse
import com.example.journeyordestination.model.Api.RetrofitInstance
import com.example.journeyordestination.view.DestinationAdapter
import com.example.journeyordestination.view.RecyclerViewFragment
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val LOG = "fragment"

class DirectionsViewModel : ViewModel() {

    val apiResponse = MutableLiveData<List<String>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun fetchData() {
        loading.value = true
        RetrofitInstance.call().enqueue(object : Callback<MapDataResponse> {
            override fun onResponse(
                call: Call<MapDataResponse>, response: Response<MapDataResponse>
            ) {
                val body = response.body()?.routes?.map {
                    it.legs.map { it.duration.text }}

                    if (body != null) {
                        apiResponse.value = body.flatten()
                        Log.i(LOG, "${apiResponse.value}")
                    }
                    error.value = null
                    loading.value = false
                }
                override fun onFailure(call: Call<MapDataResponse>, t: Throwable) {
                    onError(t.localizedMessage)
                }
            })
        }

        private fun onError(message: String?) {
            error.value = message
            loading.value = false
        }

    }




//    var job: Job? = null
//    val execptionHandeler = CoroutineExceptionHandler { coroutineContext, throwable ->
//        viewModelScope.launch(Dispatchers.Main) {
//            onError("Exception ${throwable.localizedMessage}")
//        }
//    }

//    override fun onCleared() {
//        super.onCleared()
//        job?.cancel()
//    }




//    private val _apiData = MutableLiveData<List<String>>(getDurationFromApi(response = )
//    val apiData: LiveData<List<String>> = _apiData
//
//
//    //parse response from getDirections api request and set the response body to destinationTimeData
//init {
//    val response = fetchApiResponse()
//}
//    fun fetchApiResponse(){
//        var response: Response<MapData>
//        viewModelScope.launch {
//          try {
//                RetrofitInstance.api.getDirections().body()!!
//            } catch (e: IOException) {
//                Log.e(Constants.TAG, "IOException you might not have internet connection")
//                return@launch
//            } catch (e: HttpException) {
//                Log.e(Constants.TAG, "HttpException, unexpected response")
//                return@launch
//
//            }
//        }
//    }
//
//
//    fun getDurationFromApi(response: Response<MapData>): List<String>? {
//        val list = _apiData.value
//        if (response.isSuccessful && response.body() != null) {
//            list.let {
//                response.body()!!.routes.map { it.legs.map { it.duration.text } }.flatten()
//                Log.d(Constants.TAG, "$list")
//            }
//        } else {
//            Log.e(Constants.TAG, "Response not successful")
//
//        }
//        return list
//    }


