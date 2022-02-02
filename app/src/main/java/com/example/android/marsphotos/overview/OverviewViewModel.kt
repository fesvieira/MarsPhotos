package com.example.android.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch

/** The [ViewModel] that is attached to the [OverviewFragment]. */
class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _photos = MutableLiveData<List<MarsPhoto>>()
    val photos get() = _photos

    // Gets Mars photos information from the Mars API Retrofit service
    fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = "Success: Mars properties retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}