package com.example.picturesum.display

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picturesum.data.ImageApi
import com.example.picturesum.data.ImageDetails
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class ImageApiStatus { LOADING, ERROR, DONE }
class DisplayImageViewModel : ViewModel() {

    private val _status = MutableLiveData<ImageApiStatus>()
    val status: LiveData<ImageApiStatus>
        get() = _status
    private val _properties = MutableLiveData<List<ImageDetails>>()

    val properties: LiveData<List<ImageDetails>>
        get() = _properties

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            _status.value = ImageApiStatus.LOADING
            try {
                _properties.value = ImageApi.retrofitService.getProperties()
                _status.value = ImageApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ImageApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }
}