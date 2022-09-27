package com.example.journeyordestination.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.journeyordestination.model.Api.ApiResponse.MapDataResponse
import com.example.journeyordestination.model.Api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "fragment"

class DirectionsViewModel : ViewModel() {

    val apiResponse = MutableLiveData<MutableList<String>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    init {
        fetchData()
        Log.i(TAG, "${apiResponse.value} fetched")
    }

    fun fetchData() {
        loading.value = true
        RetrofitInstance.call().enqueue(object : Callback<MapDataResponse> {
            override fun onResponse(
                call: Call<MapDataResponse>, response: Response<MapDataResponse>
            ) {
                val body = response.body()?.routes?.map {
                    it.legs.map { it.duration.text }
                }
                if (body != null) {
                    apiResponse.value = body.flatten().toMutableList()
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

//    fun removeItems(holder: DestinationAdapter.DestinationViewholder){
//        holder.textview.setOnClickListener {
//            .removeAt(holder.adapterPosition)
//            notifyDataSetChanged()
//        }
//    }

