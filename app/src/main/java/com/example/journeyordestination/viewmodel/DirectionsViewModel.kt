package com.example.journeyordestination.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.journeyordestination.model.Api.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class DirectionsViewModel : ViewModel() {

    //parse response from getDirections api request and set the response body to destinationTimeData
    fun bindApiDataToAdapter(): List<String> {
        val list = mutableListOf<String>()
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.api.getDirections()

            } catch (e: IOException) {
                Log.e(Constants.TAG, "IOException you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(Constants.TAG, "HttpException, unexpected response")
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                list += response.body()!!.routes.map { it.legs.map { it.duration.text } }.flatten()
                Log.d(Constants.TAG, "$list")
            } else {
                Log.e(Constants.TAG, "Response not successful")
            }
        }
        return list
    }
}

