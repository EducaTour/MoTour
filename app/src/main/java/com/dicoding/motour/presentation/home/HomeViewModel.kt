package com.dicoding.motour.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.dicoding.motour.data.model.landmark.list.Landmark
import com.dicoding.motour.domain.usecase.GetLandmarkListUseCase
import com.dicoding.motour.domain.usecase.UpdateLandmarkListUsecase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.UnknownHostException

class HomeViewModel(
    private val getLandmarkListUseCase: GetLandmarkListUseCase,
    private val updateLandmarkListUsecase: UpdateLandmarkListUsecase
) : ViewModel() {
    private val _data = MutableLiveData<Any>()
    val landmarkList: LiveData<Any> get() = _data

    sealed class LandmarkListState<out R> {
        data object Loading : LandmarkListState<Nothing>()
        data class Result(val landmarkList: List<Landmark?>?) :
            LandmarkListState<List<Landmark?>?>()

        data class Error(val error: String) : LandmarkListState<String>()
    }

    suspend fun getLandmarkList() {
        var landmarkList: List<Landmark?>? = null
        _data.value = LandmarkListState.Loading
        try {
            landmarkList = getLandmarkListUseCase.execute()
            _data.value = LandmarkListState.Result(landmarkList)
        } catch (e: Exception) {
            e.cause?.javaClass?.let {
                if (it.name == "java.net.UnknownHostException") {
                    _data.value = LandmarkListState.Error(it.name)
                }
            }
        }
    }

    suspend fun updateLandmarkList() {
        try {
            updateLandmarkListUsecase.execute()
        } catch (e: Exception) {
            e.cause?.javaClass?.let {
                if (it.name == "java.net.UnknownHostException") {
                    // Since it's only updating the landmark db, we don't need to notify the UI thread
                    // of network error
                }
            }
        }
    }
}