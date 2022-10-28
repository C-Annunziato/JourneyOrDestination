package com.example.journeyordestination.viewmodel


import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.journeyordestination.model.Api.ApiResponseDirections.MapDataResponse
import com.example.journeyordestination.model.Api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "fragment"

class DirectionsViewModel() : ViewModel() {

    private val _destinationaApiResponse = MutableListLiveData<String>()

    private val _loading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    private val _origin = MutableLiveData<String?>()
    private val _destination = MutableLiveData<String?>()

    val destinationApiResponse: LiveData<List<String>> = _destinationaApiResponse

    val loading: LiveData<Boolean> = _loading
    val error: LiveData<String> = _error

    var origin: LiveData<String?> = _origin
    val destination: LiveData<String?> = _destination


    fun setOrigin(string: String) {
//        Log.i(TAG, "From : ViewModel -> set origin is being called ")
//        Log.i(TAG, "From : Viewmodel -> origin prior to choice: ${origin.value}")
        _origin.value = string
//        Log.i(TAG, "From : Viewmodel -> origin after choice: ${origin.value}")
    }

    fun setDestination(string: String) {
//        Log.i(TAG, "From : ViewModel -> set dest is being called ")
//        Log.i(TAG, "From : Viewmodel -> dest prior to choice: ${destination.value}")
        _destination.value = string
//        Log.i(TAG, "From : Viewmodel -> dest after choice: ${destination.value}")
    }

    init {
//        Log.i(TAG, "From : Init Viewmodel -> origin prior to choice: ${origin.value}")
//        Log.i(TAG, "From : Init Viewmodel -> dest prior to choice: ${destination.value}")
    }

    fun launchApiRequest() {
//        viewModelScope.launch {
        fetchDirectionsData()
//        }
    }

    private fun fetchDirectionsData() {
        _loading.value = true

        RetrofitInstance.call(
            origin.value,
            destination.value,
            com.example.journeyordestination.BuildConfig.DIRECTIONS_API_KEY
        )
            .enqueue(object : Callback<MapDataResponse> {
                override fun onResponse(

                    call: Call<MapDataResponse>, response: Response<MapDataResponse>
                ) {
                    val body = response.body()?.routes?.map {
                        it.legs.map { it.duration.text }
                    }
                    Log.i(
                        TAG,
                        "${
                            RetrofitInstance.call(
                                origin.value,
                                destination.value,
                                com.example.journeyordestination.BuildConfig.DIRECTIONS_API_KEY
                            ).request()
                        }"
                    )

                    if (body != null) {
                        _destinationaApiResponse.addAll(body.flatten().toMutableList())

                        _error.value = null
                        _loading.value = false

                    }
//
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
        if (!_destinationaApiResponse.value.isNullOrEmpty()) {
            _destinationaApiResponse.removeAt(int)
        }
    }
}

// Helper class to used get the live data to update properly
class MutableListLiveData<T>(
    private val list: MutableList<T> = mutableListOf()
) : MutableList<T> by list, LiveData<List<T>>() {

    override fun add(element: T): Boolean = element.actionAndUpdate { list.add(it) }

    override fun add(index: Int, element: T) = list.add(index, element).also { updateValue() }

    override fun addAll(elements: Collection<T>): Boolean =
        elements.actionAndUpdate { list.addAll(elements) }

    override fun addAll(index: Int, elements: Collection<T>): Boolean =
        elements.actionAndUpdate { list.addAll(index, it) }

    override fun remove(element: T): Boolean = element.actionAndUpdate { list.remove(it) }

    override fun removeAt(index: Int): T = list.removeAt(index).also { updateValue() }

    override fun removeAll(elements: Collection<T>): Boolean =
        elements.actionAndUpdate { list.removeAll(it) }

    override fun retainAll(elements: Collection<T>): Boolean =
        elements.actionAndUpdate { list.retainAll(it) }

    override fun clear() = list.clear().also { updateValue() }

    override fun set(index: Int, element: T): T = list.set(index, element).also { updateValue() }

    private fun <T> T.actionAndUpdate(action: (item: T) -> Boolean): Boolean =
        action(this).applyIfTrue { updateValue() }

    private fun Boolean.applyIfTrue(action: () -> Unit): Boolean {
        takeIf { it }?.run { action() }
        return this
    }

    private fun updateValue() {
        value = list
    }
}